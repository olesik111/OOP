package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * Generic test.
 */
public class TypeTest {
    @Test
    void testGraphs() {
        var stringGraph = new AdjacencyList<String>();
        stringGraph.addEdge("1", "2");

        var intGraph = new AdjacencyList<Integer>();
        intGraph.addEdge(1, 3);

        assertDoesNotThrow(() -> stringGraph.equals(intGraph));

        assertNotEquals(stringGraph, intGraph);
    }
}
