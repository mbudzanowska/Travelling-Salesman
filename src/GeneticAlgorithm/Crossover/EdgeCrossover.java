package GeneticAlgorithm.Crossover;

import GeneticAlgorithm.Chromosome;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class EdgeCrossover implements Crossover {

    int geneNumber;

    public EdgeCrossover(int geneNumber) {
        this.geneNumber = geneNumber;
    }

    @Override
    public Pair<Chromosome, Chromosome> execute(Chromosome fistChromosome, Chromosome secondChromosome) {
        List<Integer> firstPhenotype = fistChromosome.getPhenotype();
        List<Integer> secondPhenotype = secondChromosome.getPhenotype();

        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < geneNumber; i++) {
            List<Integer> nodes = new ArrayList<>();
            int leftNeighbour = i == 0 ? geneNumber - 1 : i - 1;
            int rightNeighbour = i == geneNumber - 1 ? 0 : i + 1;
            nodes.add(firstPhenotype.get(leftNeighbour));
            nodes.add(firstPhenotype.get(rightNeighbour));
            nodes.add(secondPhenotype.get(leftNeighbour));
            nodes.add(secondPhenotype.get(rightNeighbour));
            edges.add(nodes);
        }

        Chromosome child = new Chromosome(fistChromosome);
        int random = getRandomInteger(0,geneNumber-1);
        child.getPhenotype().set(0, random);

        for(int i = 1; i<geneNumber; i++){

        }
        return null;
    }

    private int getRandomInteger(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to);
    }
}
