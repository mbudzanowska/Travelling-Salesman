package GeneticAlgorithm;

public class Main {

    public static void main(String [] args){

        Graph graph = new Graph(10);
        Chromosome chromosome = new Chromosome(graph);
        chromosome.generateRandomPhenotype();

    }
}
