package GeneticAlgorithm.Selection;

import GeneticAlgorithm.Chromosome;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TournamentSelection implements Selection {

    int populationSize;
    int tournamentSize;

    public TournamentSelection(int populationSize, int tournamentSize) {
        this.populationSize = populationSize;
        this.tournamentSize = tournamentSize;
    }

    @Override
    public List<Chromosome> execute(List<Chromosome> population, double[] data) {
        List<Chromosome> newPopulation = new ArrayList<>();
        while (newPopulation.size() != populationSize) {
            List<Integer> selectedIndexes = new ArrayList<>();
            List<Double> tournament = new ArrayList<>();
            while (tournament.size() != tournamentSize) {
                int index = getRandomInteger(0, populationSize);
                if(!selectedIndexes.contains(index)){
                    selectedIndexes.add(index);
                    tournament.add(population.get(index).getFitnessValue());
                }
            }
            int smallest = 0;
            Double smallestVal = tournament.get(0);
            for(int i = 1; i<tournament.size(); i++){
                if(tournament.get(i)<smallestVal){
                    smallestVal = tournament.get(i);
                    smallest = i;
                }
            }
            Chromosome theBest = new Chromosome(population.get(selectedIndexes.get(smallest)));
            newPopulation.add(theBest);
        }
        return newPopulation;
    }

    private int getRandomInteger(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to);
    }
}
