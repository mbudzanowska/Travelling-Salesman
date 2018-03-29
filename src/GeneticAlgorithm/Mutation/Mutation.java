package GeneticAlgorithm.Mutation;

import GeneticAlgorithm.Chromosome;

public interface Mutation {

    Chromosome execute(Chromosome chromosome);
}
