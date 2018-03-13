package GeneticAlgorithm;

import java.util.*;
import java.util.stream.Collectors;

public class Chromosome {

    private int geneNumber;
    private List<Integer> phenotype;
    private double fitnessValue = 0;
    private Graph graph;

    public Chromosome(Graph graph) {
        this.graph = graph;
        geneNumber = graph.getNodesNumber();
        phenotype = new ArrayList<>();
    }

    public Chromosome(Chromosome chromosome){
        geneNumber = chromosome.geneNumber;
        graph = chromosome.graph;
        fitnessValue = chromosome.fitnessValue;
        phenotype = new ArrayList<>();
        chromosome.phenotype.forEach(integer -> phenotype.add(integer));
    }

    public List<Integer> getPhenotype() {
        return phenotype;
    }

    public double getFitnessValue() {
        return fitnessValue;
    }

    public void calculateFitness() {
        fitnessValue = graph.getDistance(phenotype.get(0), phenotype.get(phenotype.size() - 1));
        Iterator<Integer> i1 = phenotype.listIterator();
        Iterator<Integer> i2 = phenotype.listIterator(1);
        while (i1.hasNext() && i2.hasNext()) {
            fitnessValue += graph.getDistance(i1.next(), i2.next());
        }
    }

    public void generateRandomPhenotype() {
        phenotype = java.util.stream.IntStream.rangeClosed(0, geneNumber-1).boxed().collect(Collectors.toList());
        Collections.shuffle(phenotype, new Random(System.nanoTime()));
    }

    public void swapGenes(int locus1, int locus2){
        Collections.swap(phenotype, locus1, locus2);
    }

    public void inverseGenes(int beginning, int end){
        List<Integer> sublist = phenotype.subList(beginning, end+1);
        Collections.reverse(sublist);
        for(int i = beginning; i<= end; i++){
            phenotype.set(i, sublist.get(i-beginning));
        }
    }

    public void printPhenotype(){
        for(int i = 0; i<geneNumber; i++){
            System.out.print(phenotype.get(i) + " ");
        }
        System.out.println();
    }


}
