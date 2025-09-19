package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * IncidenceMatrixTest.
 */
class IncidenceMatrixTest {

    @Test
    void testAddVertex() {
        IncidenceMatrix<String> graph = new IncidenceMatrix<>();
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("1");

        assertTrue(graph.getNeighbors("1").isEmpty());
        assertTrue(graph.getNeighbors("2").isEmpty());
    }

    @Test
    void testRemoveVertex() {
        IncidenceMatrix<String> graph = new IncidenceMatrix<>();
        graph.addEdge("1", "2");
        graph.addEdge("2", "3");
        graph.addEdge("3", "1");

        graph.removeVertex("2");

        assertTrue(graph.getNeighbors("1").isEmpty());
        assertEquals(List.of("1"), graph.getNeighbors("3"));
    }

    @Test
    void testAddEdge() {
        IncidenceMatrix<String> graph = new IncidenceMatrix<>();
        graph.addEdge("1", "2");
        graph.addEdge("1", "3");
        graph.addEdge("2", "3");

        assertEquals(List.of("2", "3"), graph.getNeighbors("1"));
        assertEquals(List.of("3"), graph.getNeighbors("2"));
        assertTrue(graph.getNeighbors("3").isEmpty());
    }

    @Test
    void testRemoveEdge() {
        IncidenceMatrix<String> graph = new IncidenceMatrix<>();
        graph.addEdge("1", "2");
        graph.addEdge("1", "3");

        graph.removeEdge("1", "2");

        assertEquals(List.of("3"), graph.getNeighbors("1"));
        assertTrue(graph.getNeighbors("2").isEmpty());
    }

    @Test
    void testGetNeighbors() {
        IncidenceMatrix<String> graph = new IncidenceMatrix<>();
        graph.addEdge("1", "2");
        graph.addEdge("1", "3");
        graph.addEdge("2", "4");

        assertEquals(List.of("2", "3"), graph.getNeighbors("1"));
        assertEquals(List.of("4"), graph.getNeighbors("2"));
        assertTrue(graph.getNeighbors("5").isEmpty());
    }

    @Test
    void testEquals() {
        IncidenceMatrix<String> graph1 = new IncidenceMatrix<>();
        graph1.addEdge("1", "2");
        graph1.addEdge("1", "3");

        IncidenceMatrix<String> graph2 = new IncidenceMatrix<>();
        graph2.addEdge("1", "2");
        graph2.addEdge("1", "3");

        IncidenceMatrix<String> graph3 = new IncidenceMatrix<>();
        graph3.addEdge("1", "2");

        assertEquals(graph1, graph2);
        assertNotEquals(graph1, graph3);
        assertNotEquals(null, graph1);
        assertEquals(graph1, graph1);
    }

    @Test
    void testToString() {
        IncidenceMatrix<String> graph = new IncidenceMatrix<>();
        graph.addEdge("2", "1");
        graph.addEdge("1", "3");

        String result = graph.toString();
        assertTrue(result.contains("IncidenceMatrixGraph"));
        assertTrue(result.contains("Vertices:"));
        assertTrue(result.contains("Edges:"));
        assertTrue(result.contains("1 -> 3") || result.contains("2 -> 1"));
    }
}