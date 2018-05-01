package GeneticAlgorithm.Selection;

import GeneticAlgorithm.Chromosome;

import java.util.ArrayList;
import java.util.List;

public class ElitistSelection extends TournamentSelection {

    int populationSize;
    int tournamentSize;
    int elitismSize;

    public ElitistSelection(int populationSize, int tournamentSize, int elitismSize) {
        super(populationSize - elitismSize, tournamentSize);
        this.populationSize = populationSize;
        this.tournamentSize = tournamentSize;
        this.elitismSize = elitismSize;
    }

    @Override
    public List<Chromosome> execute(List<Chromosome> population, double[] data) {
        List<Chromosome> newPopulation = new ArrayList<>();
        population.sort((ch1, ch2) -> (int) (ch1.getFitnessValue() - ch2.getFitnessValue()));
        for (int i = 0; i < elitismSize; i++) {
            newPopulation.add(new Chromosome(population.remove(0)));
        }
        List<Chromosome> populationFromTournament = super.execute(population, data);
        newPopulation.addAll(populationFromTournament);
        return newPopulation;
    }
}
