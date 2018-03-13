package GeneticAlgorithm.Selection;

import GeneticAlgorithm.Chromosome;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RouletteSelection implements Selection {

    private int populationSize;

    public RouletteSelection(int populationSize){
        this.populationSize = populationSize;
    }

    //(datas[iteration - 1][3]
    @Override
    public List<Chromosome> execute(List<Chromosome> population, double [] data) {
        List<Double> sectorSizes = new ArrayList<>();
        List<Chromosome> newPopulation = new ArrayList<>();
        double rouletteSum = 0.0;
        for (Chromosome chromosome : population) {
            double value = ((data[3] - chromosome.getFitnessValue() + 1) / (data[3] + 1)) * 100;
            sectorSizes.add(value);
            rouletteSum += value;
        }
        for (int i = 0; i < populationSize; i++) {
            double randomValue = ThreadLocalRandom.current().nextDouble(0, rouletteSum);
            int index = -1;
            double helperSum = 0.0;
            while (helperSum <= randomValue) {
                index++;
                helperSum += sectorSizes.get(index);
            }
            newPopulation.add(new Chromosome(population.get(index)));
        }
        return newPopulation;
    }
}
