package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

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


        AdjacencyMatrix<Integer> graph2 = new AdjacencyMatrix<>();
        graph2.addVertex(1);
        graph2.addVertex(2);
        graph2.addEdge(1, 2);

        assertNotEquals(graph1, graph2);
    }

    @Test
    void testNotEqualsDifferentVertices() {
        AdjacencyMatrix<String> graph1 = new AdjacencyMatrix<>();
        graph1.addVertex("1");

        AdjacencyMatrix<Integer> graph2 = new AdjacencyMatrix<>();
        graph2.addVertex(1);

        assertNotEquals(graph1, graph2);
    }
}
