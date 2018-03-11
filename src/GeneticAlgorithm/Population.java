package GeneticAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Population {

    private enum mutation {
        INVERSE,
        SCRAMBLE
    }

    private enum crossover {
        PARTIALLY_MATCHED,
        ORDER,
        EDGE
    }

    private static final int POPULATION_SIZE = 1;
    private static final int GRAPH_SIZE = 20;
    private static final double CROSSOVER_PROBABILITY = 0.75;
    private static final double MUTATION_RPOBABILTY = 0.01;
    private static final int ITERATIONS_NUMBER = 100;
    private static int SCRAMBLE_MUTATION_SIZE = 5;


    private List<Chromosome> population;
    private Graph graph;

    public Population() {
        population = new ArrayList<>();
    }

    public void doThisShit() {

        initializeGraph();
        initializePopulation();
        reevaluateFitness();
        population.get(0).printPhenotype();
        doScrambleMutation(population.get(0));
        population.get(0).printPhenotype();

    }

    public void initializeGraph() {
        graph = new Graph(GRAPH_SIZE);
    }

    public void initializePopulation() {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            Chromosome chromosome = new Chromosome(graph);
            chromosome.generateRandomPhenotype();
            population.add(chromosome);
        }
    }

    public void performChromosomeSelection() {

    }

    public void performChromosomeCrossing() {

    }

    public void performChromosomeMutation() {

    }

    public void reevaluateFitness() {
        for (Chromosome chromosome : population) {
            chromosome.calculateFitness();
        }
    }

    public void doScrambleMutation(Chromosome chromosome) {
        List<Integer> locuses = new ArrayList<>();
        while (locuses.size() != SCRAMBLE_MUTATION_SIZE) {
            int locus = getRandomInteger(0, GRAPH_SIZE - 1);
            if (!locuses.contains(locus)) {
                locuses.add(locus);
            }
        }
        for (int i = 0; i < SCRAMBLE_MUTATION_SIZE; i++) {
            chromosome.swapGenes(locuses.get(getRandomInteger(0, SCRAMBLE_MUTATION_SIZE - 1)), locuses.get(getRandomInteger(0, SCRAMBLE_MUTATION_SIZE - 1)));
        }
    }

    public void doInverseMutation(Chromosome chromosome) {
        int beginning = getRandomInteger(0, GRAPH_SIZE - 1);
        int end = getRandomInteger(0, GRAPH_SIZE - 1);
        chromosome.inverseGenes(beginning, end);

    }

    private int getRandomInteger(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to + 1);
    }
}
