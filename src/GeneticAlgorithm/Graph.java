package GeneticAlgorithm;

import java.util.concurrent.ThreadLocalRandom;

public class Graph {

    private int nodesNumber;
    private double[][] matrix;
    private static final double MIN_DISTANCE = 1;
    private static final double MAX_DISTANCE = 10;

    public Graph(int nodesNumber) {
        this.nodesNumber = nodesNumber;
        matrix = new double[nodesNumber][nodesNumber];
        readMatrix();
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public int getNodesNumber() {
        return nodesNumber;
    }

    private void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public double getDistance(int firstNode, int secondNode) {
        return matrix[firstNode][secondNode];
    }

    private void generateRandomMatrixAndSave(){
        for(int i = 0; i<nodesNumber; i++){
            for(int j = i; j<nodesNumber; j++){
                double random = ThreadLocalRandom.current().nextInt(min, max + 1);
            }
        }
    }

    public void readMatrix(){

    }

}
