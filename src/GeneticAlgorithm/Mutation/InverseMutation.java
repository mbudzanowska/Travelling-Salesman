package GeneticAlgorithm.Mutation;

import GeneticAlgorithm.Chromosome;

import java.util.concurrent.ThreadLocalRandom;

public class InverseMutation implements Mutation {

    private int graphSize;

    public InverseMutation(int graphSize){
        this.graphSize = graphSize;
    }

    @Override
    public Chromosome execute(Chromosome chromosome) {
        Chromosome mutated = new Chromosome(chromosome);
        int beginning = getRandomInteger(0, graphSize - 1);
        int end = getRandomInteger(0, graphSize - 1);
        if(beginning > end){
            int temp = beginning;
            beginning = end;
            end = temp;
        }
        mutated.inverseGenes(beginning, end);
        return mutated;
    }

    private int getRandomInteger(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to + 1);
    }
}
