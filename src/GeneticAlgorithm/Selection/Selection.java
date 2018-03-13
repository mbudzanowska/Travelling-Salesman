package GeneticAlgorithm.Selection;

import GeneticAlgorithm.Chromosome;

import java.util.List;

public interface Selection {

    List<Chromosome> execute(List<Chromosome> population, double [] data);
}
