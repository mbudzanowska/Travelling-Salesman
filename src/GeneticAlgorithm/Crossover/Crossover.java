package GeneticAlgorithm.Crossover;

import GeneticAlgorithm.Chromosome;
import javafx.util.Pair;

public interface Crossover {

    public Pair<Chromosome, Chromosome> execute(Chromosome fistChromosome, Chromosome secondChromosome);
}
