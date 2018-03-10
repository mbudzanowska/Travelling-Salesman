package GeneticAlgorithm;

import java.util.concurrent.ThreadLocalRandom;

public class Graph {

    private int nodesNumber;
    private double[][] matrix;
    private static final int MIN_DISTANCE = 1;
    private static final int MAX_DISTANCE = 10;

    public Graph(int nodesNumber) {
        this.nodesNumber = nodesNumber;
        matrix = new double[nodesNumber][nodesNumber];
        readMatrix();
    }

    public int getNodesNumber() {
        return nodesNumber;
    }

    public double getDistance(int firstNode, int secondNode) {
        return matrix[firstNode][secondNode];
    }

    private void generateRandomMatrixAndSave(){
        for(int i = 0; i<nodesNumber; i++){
            for(int j = i; j<nodesNumber; j++){
                double random = ThreadLocalRandom.current().nextInt(MIN_DISTANCE, MAX_DISTANCE + 1);

            }
        }
    }

    public void readMatrix(){

    }

}
