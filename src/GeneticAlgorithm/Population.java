package GeneticAlgorithm;

import GeneticAlgorithm.Crossover.Crossover;
import GeneticAlgorithm.Crossover.EdgeCrossover;
import GeneticAlgorithm.Crossover.OrderCrossover;
import GeneticAlgorithm.Crossover.PartiallyMatchedCrossover;
import GeneticAlgorithm.Mutation.InverseMutation;
import GeneticAlgorithm.Mutation.Mutation;
import GeneticAlgorithm.Mutation.ScrambleMutation;
import GeneticAlgorithm.Selection.RouletteSelection;
import GeneticAlgorithm.Selection.Selection;
import GeneticAlgorithm.Selection.TournamentSelection;
import javafx.util.Pair;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Population {

    private enum mutation_type {
        INVERSE,
        SCRAMBLE
    }

    private enum crossover_type {
        PARTIALLY_MATCHED,
        ORDER,
        EDGE
    }

    private enum selection_type {
        ROULETTE,
        TOURNAMENT
    }

    private static final int POPULATION_SIZE = 100;
    private static final int GRAPH_SIZE = 100;
    private static final int MIN_DISTANCE = 1;
    private static final int MAX_DISTANCE = 100;
    private static final double CROSSOVER_PROBABILITY = 0.8;
    private static final double MUTATION_PROBABILITY = 0.05;
    private static final int ITERATIONS_NUMBER = 1000;
    private static final int SCRAMBLE_MUTATION_SIZE = 5;
    private static final int TOURNAMENT_SIZE = 10;
    private static final mutation_type MUTATION = mutation_type.INVERSE;
    private static final crossover_type CROSSOVER = crossover_type.ORDER;
    private static final selection_type SELECTION = selection_type.TOURNAMENT;

    private static List<Chromosome> population;
    private Graph graph;
    private double[][] data;
    private Chromosome bestChromosome;

    private Crossover crossover;
    private Mutation mutation;
    private Selection selection;

    public Population() {
        population = new ArrayList<>();
        data = new double[ITERATIONS_NUMBER + 1][4];
        setCrossover();
        setMutation();
        setSelection();
    }

    private void setCrossover() {
        switch (CROSSOVER) {
            case PARTIALLY_MATCHED:
                crossover = new PartiallyMatchedCrossover();
                break;
            case ORDER:
                crossover = new OrderCrossover(GRAPH_SIZE);
                break;
            case EDGE:
                crossover = new EdgeCrossover();
                break;
        }
    }

    private void setMutation() {
        switch (MUTATION) {
            case INVERSE:
                mutation = new InverseMutation(GRAPH_SIZE);
                break;
            case SCRAMBLE:
                mutation = new ScrambleMutation(SCRAMBLE_MUTATION_SIZE, GRAPH_SIZE);
                break;
        }

    }

    private void setSelection() {
        switch (SELECTION) {
            case ROULETTE:
                selection = new RouletteSelection(POPULATION_SIZE);
                break;
            case TOURNAMENT:
                selection = new TournamentSelection(POPULATION_SIZE, TOURNAMENT_SIZE);
                break;
        }
    }

    public void performOptimization() {
        initializeGraph();
        initializePopulation();
        reevaluateFitness();
        gatherData(0);
        for (int i = 1; i <= ITERATIONS_NUMBER; i++) {
            performChromosomeSelection(i);
            performChromosomeCrossing();
            performChromosomeMutation();
            reevaluateFitness();
            gatherData(i);
        }
        try {
            saveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(bestChromosome.getFitnessValue());
    }

    public void initializeGraph() {
        graph = new Graph(GRAPH_SIZE, MIN_DISTANCE, MAX_DISTANCE);
    }

    public void initializePopulation() {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            Chromosome chromosome = new Chromosome(graph);
            chromosome.generateRandomPhenotype();
            population.add(chromosome);
        }
    }

    public void performChromosomeSelection(int iteration) {
        population = selection.execute(population, data[iteration - 1]);
    }

    public void performChromosomeCrossing() {
        List<Chromosome> crossoveredPopupation = new ArrayList<>();
        while (population.size() > 1) {
            Chromosome firstChromosome = population.remove(0);
            double probability = new Random().nextDouble();
            if (probability <= CROSSOVER_PROBABILITY) {
                int secondChromosomeIndex = ThreadLocalRandom.current().nextInt(0, population.size());
                Chromosome secondChromosome = population.remove(secondChromosomeIndex);
                Pair<Chromosome, Chromosome> children = crossover.execute(firstChromosome, secondChromosome);
                crossoveredPopupation.add(children.getKey());
                crossoveredPopupation.add(children.getValue());
//                System.out.print("before: ");
//                firstChromosome.printPhenotype();
//                System.out.print("before: ");
//                secondChromosome.printPhenotype();
//                System.out.print("after: ");
//                children.getKey().printPhenotype();
//                System.out.print("after: ");
                //children.getValue().printPhenotype();
            } else {
                crossoveredPopupation.add(new Chromosome(firstChromosome));
            }
        }
        if (population.size() == 1) {
            crossoveredPopupation.add(population.remove(0));
        }
        population = crossoveredPopupation;
    }

    public void performChromosomeMutation() {
        List<Chromosome> mutatedPopulation = new ArrayList<>();
        population.forEach(chromosome -> {
            double probability = new Random().nextDouble();
            Chromosome mutated;
            if (probability <= MUTATION_PROBABILITY) {
                mutated = mutation.execute(chromosome);
              /*  System.out.print("before: ");
                chromosome.printPhenotype();
                System.out.print("after: ");
                mutated.printPhenotype();*/
            } else {
                mutated = new Chromosome(chromosome);
            }
            mutatedPopulation.add(mutated);


        });

        population = mutatedPopulation;
    }

    public void reevaluateFitness() {
        for (Chromosome chromosome : population) {
            chromosome.calculateFitness();
        }
    }

    private void gatherData(int iteration) {
        Double sum = 0.0;
        Double bestValue = Double.POSITIVE_INFINITY;
        Double worstValue = Double.NEGATIVE_INFINITY;
        for (Chromosome chromosome : population) {
            double fitnessValue = chromosome.getFitnessValue();
            sum += fitnessValue;
            if (bestValue > fitnessValue) {
                bestValue = fitnessValue;
                bestChromosome = chromosome;
            }
            if (worstValue < fitnessValue) {
                worstValue = fitnessValue;
            }
            data[iteration][0] = sum;
            data[iteration][1] = sum / POPULATION_SIZE;
            data[iteration][2] = bestValue;
            data[iteration][3] = worstValue;
        }
    }

    private void saveData() throws IOException {
        BufferedWriter outputWriter = new BufferedWriter(new FileWriter("fitness_values.txt"));
        for (int i = 0; i < ITERATIONS_NUMBER - 1; i++) {
            for (int j = 0; j < 4; j++) {
                outputWriter.write(Double.toString(data[i][j]) + " ");
            }
            outputWriter.newLine();
        }
        outputWriter.flush();
        outputWriter.close();
    }
}
