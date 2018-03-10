package Tests;

import GeneticAlgorithm.Chromosome;
import GeneticAlgorithm.Graph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;


public class ChromosomeTests {

    Graph graph;
    Chromosome chromosome;
    int geneNumber;
    int nodesNumber;

    @Before
    public void setUp() {
        nodesNumber = 10;
        graph = new Graph(nodesNumber);
        chromosome = new Chromosome(graph);
        geneNumber = 10;
    }

    @Test
    public void shouldBeAbleToCreateChromosome() {
        assertNotNull(chromosome);
    }

    @Test
    public void shouldBeAbleToGetPhenotype() {
        int[] phenotype = chromosome.getPhenotype();
        assertNotNull(phenotype);
    }

    @Test
    public void phenotypeShouldBeProperLength() {
        int[] phenotype = chromosome.getPhenotype();
        assertEquals(geneNumber, phenotype.length);
    }

    @Test
    public void shouldGetFitnessValue() {
        assertNotNull(chromosome.getFitnessValue());
    }

    @Test
    public void fitnessValueShouldNotBeZero() {
        int fitnessValue = chromosome.getFitnessValue();
        assertNotEquals(0, fitnessValue);
    }
}
