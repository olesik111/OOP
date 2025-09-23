package ru.nsu.kataeva;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Type erasing tests.
 */
public class TypeTest {
    @Test
    void testEqualsDifferentGenericTypes() {
        AdjacencyMatrix<String> graph1 = new AdjacencyMatrix<>();
        graph1.addVertex("A");
        graph1.addVertex("B");
        graph1.addEdge("A", "B");


        AdjacencyMatrix<Object> graph2 = new AdjacencyMatrix<>();
        graph2.addVertex("A");
        graph2.addVertex("B");
        graph2.addEdge("A", "B");

        assertEquals(graph1, graph2);
        assertEquals(graph2, graph1);
    }

    @Test
    void testNotEqualsDifferentVertices() {
        AdjacencyMatrix<String> graph1 = new AdjacencyMatrix<>();
        graph1.addVertex("A");

        AdjacencyMatrix<Object> graph2 = new AdjacencyMatrix<>();
        graph2.addVertex("X");

        assertNotEquals(graph1, graph2);
    }
}
