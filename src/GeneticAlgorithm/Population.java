package GeneticAlgorithm;

import java.util.List;

public class Population {

    private static final int POPULATION_SIZE = 4;
    private static final int GRAPH_SIZE = 20;
    private static final double CROSSOVER_PROBABILITY = 0.75;
    private static final double MUTATION_RPOBABILTY = 0.01;
    private static final int ITERATIONS_NUMBER = 100;

    private List<Chromosome> population;



    public void doThisShit(){

    }


    public void initializeGraph(){

    }

    public void initializePopulation(){

    }

    public void performChromosomeSelection(){

    }

    public void performChromosomeCrossing(){

    }

    public void performChromosomeMutation(){

    }

    public void reevaluateFitness(){
        for(Chromosome chromosome : population) {
            chromosome.calculateFitness();
        }
    }
}
