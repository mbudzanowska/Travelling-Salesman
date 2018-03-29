package GeneticAlgorithm.Mutation;

import GeneticAlgorithm.Chromosome;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ScrambleMutation implements Mutation{

    private int scrambleMutationSize;
    private int graphSize;

    public ScrambleMutation(int scrambleMutationSize, int graphSize){
        this.scrambleMutationSize = scrambleMutationSize;
        this.graphSize = graphSize;
    }

    @Override
    public Chromosome execute(Chromosome chromosome) {
        Chromosome mutated = new Chromosome(chromosome);
        List<Integer> locuses = new ArrayList<>();
        while (locuses.size() != scrambleMutationSize) {
            int locus = getRandomInteger(0, graphSize - 1);
            if (!locuses.contains(locus)) {
                locuses.add(locus);
            }
        }
        for (int i = 0; i < scrambleMutationSize; i++) {
            mutated.swapGenes(locuses.get(getRandomInteger(0, scrambleMutationSize - 1)), locuses.get(getRandomInteger(0, scrambleMutationSize - 1)));
        }
        return mutated;
    }

    private int getRandomInteger(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to + 1);
    }
}
