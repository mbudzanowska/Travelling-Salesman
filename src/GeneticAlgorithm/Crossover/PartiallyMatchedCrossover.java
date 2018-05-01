package GeneticAlgorithm.Crossover;

import GeneticAlgorithm.Chromosome;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PartiallyMatchedCrossover extends OrderCrossover implements Crossover {

    public PartiallyMatchedCrossover(int geneNumber) {
        super(geneNumber);
    }

    @Override
    public Pair<Chromosome, Chromosome> execute(Chromosome fistChromosome, Chromosome secondChromosome) {
        generateLocuses();
        Chromosome firstChild = createChild(fistChromosome, secondChromosome, firstLocus, secondLocus);
        Chromosome secondChild = createChild(secondChromosome, fistChromosome, firstLocus, secondLocus);
        return new Pair<>(firstChild, secondChild);
    }

    private Chromosome createChild(Chromosome firstChromosome, Chromosome secondChromosome, int firstLocus, int secondLocus) {

        List<Integer> firstPhenotype = firstChromosome.getPhenotype();
        List<Integer> secondPhenotype = secondChromosome.getPhenotype();
        Chromosome child = new Chromosome(firstChromosome);
        List<Integer> inheritedGenes = new ArrayList<>();
        for (int i = firstLocus; i <= secondLocus; i++) {
            inheritedGenes.add(firstChromosome.getPhenotype().get(i));
        }
        for (int i = 0; i < firstLocus; i++) {
            int gene = secondPhenotype.get(i);
            while (inheritedGenes.contains(gene)) {
                int gene_placement = firstPhenotype.indexOf(gene);
                gene = secondPhenotype.get(gene_placement);
            }
            child.getPhenotype().set(i, gene);
        }
        for (int i = secondLocus + 1; i < geneNumber; i++) {
            int gene = secondPhenotype.get(i);
            while (inheritedGenes.contains(gene)) {
                int gene_placement = firstPhenotype.indexOf(gene);
                gene = secondPhenotype.get(gene_placement);
            }
            child.getPhenotype().set(i, gene);
        }
        return child;
    }
}
