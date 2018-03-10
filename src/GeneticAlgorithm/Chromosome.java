package GeneticAlgorithm;

import java.util.*;
import java.util.stream.Collectors;

public class Chromosome {

    private int geneNumber;
    List<Integer> phenotype;
    private double fitnessValue = 0;
    private Graph graph;

    public Chromosome(Graph graph) {
        geneNumber = graph.getNodesNumber();
        phenotype = new ArrayList<>();
    }

    public List<Integer> getPhenotype() {
        return phenotype;
    }

    public double getFitnessValue() {
        return fitnessValue;
    }

    public void calculateFitness() {
        fitnessValue = graph.getDistance(phenotype.get(0), phenotype.get(phenotype.size()-1));
        Iterator<Integer> i1 = phenotype.listIterator();
        Iterator<Integer> i2 = phenotype.listIterator(1);
        while(i1.hasNext() && i2.hasNext()){
            fitnessValue += graph.getDistance(i1.next(), i2.next());
        }
    }

    public void generateRandomPhenotype(){
       phenotype = java.util.stream.IntStream.rangeClosed(0, geneNumber).boxed().collect(Collectors.toList());
       Collections.shuffle(phenotype, new Random(System.nanoTime()));
       System.out.println(phenotype);
    }


}
