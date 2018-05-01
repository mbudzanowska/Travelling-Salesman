package GeneticAlgorithm.Crossover;

import GeneticAlgorithm.Chromosome;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class OrderCrossover implements Crossover {

    protected int geneNumber;
    protected int firstLocus;
    protected int secondLocus;

    public OrderCrossover(int geneNumber) {
        this.geneNumber = geneNumber;
    }

    @Override
    public Pair<Chromosome, Chromosome> execute(Chromosome fistChromosome, Chromosome secondChromosome) {
        Chromosome firstChild = createChild(fistChromosome, secondChromosome, firstLocus, secondLocus);
        Chromosome secondChild = createChild(secondChromosome, fistChromosome, firstLocus, secondLocus);
        return new Pair<>(firstChild, secondChild);
    }

    private Chromosome createChild(Chromosome firstChromosome, Chromosome secondChromosome, int firstLocus, int secondLocus) {
        Chromosome child = new Chromosome(firstChromosome);
        List<Integer> inheritedGenes = new ArrayList<>();
        for (int i = firstLocus; i <= secondLocus; i++) {
            inheritedGenes.add(firstChromosome.getPhenotype().get(i));
        }
        int firstChromosomeGeneIndex = secondLocus == geneNumber - 1? 0: secondLocus + 1;
        int secondChromosomeGeneIndex = firstChromosomeGeneIndex;
        int numberOfGenesInherited = inheritedGenes.size();
        while (numberOfGenesInherited != geneNumber) {
            int gene = secondChromosome.getPhenotype().get(secondChromosomeGeneIndex);
            if (!inheritedGenes.contains(gene)) {
                child.getPhenotype().set(firstChromosomeGeneIndex, gene);
                firstChromosomeGeneIndex = firstChromosomeGeneIndex == geneNumber - 1 ? 0 : firstChromosomeGeneIndex + 1;
                numberOfGenesInherited++;
            }
            secondChromosomeGeneIndex = secondChromosomeGeneIndex == geneNumber - 1 ? 0 : secondChromosomeGeneIndex + 1;
        }
        return child;
    }

    protected void generateLocuses(){
        firstLocus = getRandomInteger(0, geneNumber-1);
        secondLocus = -1;
        while(secondLocus < 0 || secondLocus == firstLocus) {
            secondLocus = getRandomInteger(0, geneNumber-1);
        }
        if(secondLocus < firstLocus) {
            int helper = firstLocus;
            firstLocus = secondLocus;
            secondLocus = helper;
        }
    }

    private int getRandomInteger(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to);
    }
}
