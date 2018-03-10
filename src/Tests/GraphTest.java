package Tests;
import GeneticAlgorithm.Graph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraphTest {

    Graph graph;
    int nodesNumber;

    @Before
    public void setUp(){
        nodesNumber = 10;
        graph = new Graph(nodesNumber);

    }

    @Test
    public void shouldBeAbleToCreateGraph(){
        assertNotNull(graph);
    }

    @Test
    public void shouldCreateNodesMatrix(){
        assertNotNull(graph.getMatrix());
    }

    @Test
    public void shouldHaveProperSizeMatrix(){
        double [][] matrix = graph.getMatrix();
        assertTrue(nodesNumber == matrix.length && nodesNumber == matrix[0].length);
    }

    @Test
    public void matrixShouldNotBeZeroLength(){
        double [][] matrix = graph.getMatrix();
        assertTrue(0 != matrix.length && 0 != matrix[0].length);
    }

    @Test
    public void shouldGetNodesNumber(){
        assertEquals(nodesNumber, graph.getNodesNumber());
    }
    
    @Test
    public void shoudBeAbleToSetMatrix() {
        double[][] matrix =
                        {{Double.POSITIVE_INFINITY, 1, 2},
                        {1, Double.POSITIVE_INFINITY, 3},
                        {2, 3, Double.POSITIVE_INFINITY}};
        graph.setMatrix(matrix);

    }
    
    @Test
    public void shouldGetNodesDistance(){
        
    }

}
