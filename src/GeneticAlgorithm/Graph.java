package GeneticAlgorithm;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class Graph {

    private int nodesNumber;
    private double[][] matrix;
    private static final int MIN_DISTANCE = 1;
    private static final int MAX_DISTANCE = 10;
    private static final String FILE_NAME = "src/Graphs/graph_";

    public Graph(int nodesNumber) {
        this.nodesNumber = nodesNumber;
        matrix = new double[nodesNumber][nodesNumber];
        loadMatrix();
    }

    public int getNodesNumber() {
        return nodesNumber;
    }

    public double getDistance(int firstNode, int secondNode) {
        return matrix[firstNode][secondNode];
    }

    public void generateRandomMatrix() {
        for (int i = 0; i < nodesNumber; i++) {
            for (int j = i; j < nodesNumber; j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                } else {
                    double distance = ThreadLocalRandom.current().nextInt(MIN_DISTANCE, MAX_DISTANCE + 1);
                    matrix[i][j] = matrix[j][i] = distance;
                }
            }
        }
    }

    private void saveMatrix(String filename) throws IOException {
        BufferedWriter outputWriter = new BufferedWriter(new FileWriter(filename));
        for (int i = 0; i < nodesNumber; i++) {
            for (int j = 0; j < nodesNumber; j++) {
                outputWriter.write(Double.toString(matrix[i][j]) + " ");
            }
            outputWriter.newLine();
        }
        outputWriter.flush();
        outputWriter.close();
    }

    public void printOutMatrix() {
        for (int i = 0; i < nodesNumber; i++) {
            for (int j = 0; j < nodesNumber; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void readMatrix(String filename) throws IOException {
        BufferedReader inputReader = new BufferedReader(new FileReader(filename));
        for (int i = 0; i < nodesNumber; i++) {
            String[] line = inputReader.readLine().split(" ");
            for (int j = 0; j < nodesNumber; j++) {
                matrix[i][j] = Double.parseDouble(line[j]);
            }
        }
    }

    public void loadMatrix() {
        try {
            readMatrix(FILE_NAME + nodesNumber);
        } catch (IOException e1) {
            generateRandomMatrix();
            try {
                saveMatrix(FILE_NAME + nodesNumber);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

}
