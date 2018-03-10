package GeneticAlgorithm;

public class Chromosome {

    private int geneNumber;
    private int[] phenotype;
    private double fitnessValue = 0;
    private Graph graph;

    public Chromosome(Graph graph) {
        geneNumber = graph.getNodesNumber();
        phenotype = new int[geneNumber];
    }

    public int[] getPhenotype() {
        return phenotype;
    }

    public double getFitnessValue() {
        return fitnessValue;
    }

    public void calculateFitness() {
        double fitness = 0;
        for (int i = 0; i < geneNumber - 1; i++) {
            fitness += graph.getDistance(phenotype[i], phenotype[i + 1]);
        }
        if(geneNumber>1) {
            fitness += graph.getDistance(phenotype[0], phenotype[geneNumber-1]);
        }
        fitnessValue = fitness;
    }

    public void generateRandomPhenotype(){

    }


}
