package GeneticAlgorithm.Crossover;

import GeneticAlgorithm.Chromosome;
import javafx.util.Pair;

public interface Crossover {

    Pair<Chromosome, Chromosome> execute(Chromosome fistChromosome, Chromosome secondChromosome);
}
