package GeneticAlgorithm.Mutation;

import GeneticAlgorithm.Chromosome;

import java.util.concurrent.ThreadLocalRandom;

public class InverseMutation implements Mutation {

    private int graphSize;

    public InverseMutation(int graphSize){
        this.graphSize = graphSize;
    }

    @Override
    public void execute(Chromosome chromosome) {
        int beginning = getRandomInteger(0, graphSize - 1);
        int end = getRandomInteger(0, graphSize - 1);
        chromosome.inverseGenes(beginning, end);
    }

    private int getRandomInteger(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to + 1);
    }
}
