package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * AdjacencyMatrixTest.
 */
class AdjacencyMatrixTest {

    @Test
    void testAddVertex() {
        AdjacencyMatrix<String> graph = new AdjacencyMatrix<>();
        graph.addVertex("5");
        graph.addVertex("15");

        assertTrue(graph.getNeighbors("5").isEmpty());
        assertTrue(graph.getNeighbors("15").isEmpty());
    }

    @Test
    void testRemoveVertex() {
        AdjacencyMatrix<String> graph = new AdjacencyMatrix<>();
        graph.addEdge("1", "2");
        graph.addEdge("2", "3");
        graph.addEdge("3", "1");

        graph.removeVertex("2");

        assertTrue(graph.getNeighbors("1").isEmpty());
    }

    @Test
    void testAddEdge() {
        AdjacencyMatrix<String> graph = new AdjacencyMatrix<>();
        graph.addEdge("1", "2");
        graph.addEdge("1", "3");
        graph.addEdge("2", "3");

        assertEquals(List.of("2", "3"), graph.getNeighbors("1"));
        assertEquals(List.of("3"), graph.getNeighbors("2"));
        assertTrue(graph.getNeighbors("3").isEmpty());
    }

    @Test
    void testRemoveEdge() {
        AdjacencyMatrix<String> graph = new AdjacencyMatrix<>();
        graph.addEdge("1", "2");
        graph.addEdge("1", "3");

        graph.removeEdge("1", "2");

        assertEquals(List.of("3"), graph.getNeighbors("1"));
        assertTrue(graph.getNeighbors("2").isEmpty());
    }

    @Test
    void testGetNeighbors() {
        AdjacencyMatrix<String> graph = new AdjacencyMatrix<>();
        graph.addEdge("1", "2");
        graph.addEdge("1", "3");
        graph.addEdge("2", "4");

        assertEquals(List.of("2", "3"), graph.getNeighbors("1"));
        assertEquals(List.of("4"), graph.getNeighbors("2"));
        assertTrue(graph.getNeighbors("5").isEmpty());
    }

    @Test
    void testEquals() {
        AdjacencyMatrix<String> graph1 = new AdjacencyMatrix<>();
        graph1.addEdge("1", "2");
        graph1.addEdge("1", "3");

        AdjacencyMatrix<String> graph2 = new AdjacencyMatrix<>();
        graph2.addEdge("1", "2");
        graph2.addEdge("1", "3");

        AdjacencyMatrix<String> graph3 = new AdjacencyMatrix<>();
        graph3.addEdge("1", "2");

        assertEquals(graph1, graph2);
        assertNotEquals(graph1, graph3);
        assertNotEquals(null, graph1);
        assertEquals(graph1, graph1);
    }

    @Test
    void testToString() {
        AdjacencyMatrix<String> graph = new AdjacencyMatrix<>();
        graph.addEdge("0", "1");
        graph.addEdge("1", "2");

        String result = graph.toString();
        assertTrue(result.contains("AdjacencyMatrixGraph"));
        assertTrue(result.contains("0"));
        assertTrue(result.contains("1"));
        assertTrue(result.contains("2"));
    }
}