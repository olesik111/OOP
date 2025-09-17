package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * AdjacencyListTest.
 */
class AdjacencyListTest {

    @Test
    void testAddVertex() {
        AdjacencyList graph = new AdjacencyList();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(1);

        assertTrue(graph.getNeighbors(1).isEmpty());
        assertTrue(graph.getNeighbors(2).isEmpty());
    }

    @Test
    void testRemoveVertex() {
        AdjacencyList graph = new AdjacencyList();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        graph.removeVertex(2);

        assertFalse(graph.getNeighbors(1).contains(2));
        assertTrue(graph.getNeighbors(3).contains(1));
        assertEquals(List.of(1), graph.getNeighbors(3));
    }

    @Test
    void testAddEdge() {
        AdjacencyList graph = new AdjacencyList();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        assertEquals(List.of(2, 3), graph.getNeighbors(1));
        assertEquals(List.of(3), graph.getNeighbors(2));
        assertTrue(graph.getNeighbors(3).isEmpty());
    }

    @Test
    void testRemoveEdge() {
        AdjacencyList graph = new AdjacencyList();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 2);

        graph.removeEdge(1, 2);

        assertEquals(List.of(3), graph.getNeighbors(1));
        assertTrue(graph.getNeighbors(2).isEmpty());
    }

    @Test
    void testGetNeighbors() {
        AdjacencyList graph = new AdjacencyList();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);

        assertEquals(List.of(2, 3), graph.getNeighbors(1));
        assertEquals(List.of(4), graph.getNeighbors(2));
        assertTrue(graph.getNeighbors(5).isEmpty());
    }

    @Test
    void testTopologicalSort() {
        AdjacencyList graph = new AdjacencyList();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        List<Integer> result = graph.topologicalSort();
        assertTrue(result.indexOf(2) < result.indexOf(4));
        assertTrue(result.indexOf(3) < result.indexOf(4));
    }

    @Test
    void testEquals() {
        AdjacencyList graph1 = new AdjacencyList();
        graph1.addEdge(1, 2);
        graph1.addEdge(1, 3);

        AdjacencyList graph2 = new AdjacencyList();
        graph2.addEdge(1, 2);
        graph2.addEdge(1, 3);

        AdjacencyList graph3 = new AdjacencyList();
        graph3.addEdge(1, 2);

        assertEquals(graph1, graph2);
        assertNotEquals(graph1, graph3);
        assertNotEquals(null, graph1);
        assertEquals(graph1, graph1);
    }

    @Test
    void testToString() {
        AdjacencyList graph = new AdjacencyList();
        graph.addEdge(2, 1);
        graph.addEdge(1, 3);

        String result = graph.toString();
        assertTrue(result.contains("1 -> [3]"));
        assertTrue(result.contains("2 -> [1]"));
        assertTrue(result.contains("3 -> []"));
    }

    @TempDir
    Path tempDir;

    @Test
    void readValidGraphTest() throws Exception {
        Graph graph = new AdjacencyList();
        File testFile = tempDir.resolve("graph.txt").toFile();

        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("1 2\n");
            writer.write("2 3\n");
        }

        GraphFileReader.readFromFile(graph, testFile.getAbsolutePath());
        assertNotNull(graph.toString());
    }

    @Test
    void readEmptyFileTest() throws Exception {
        Graph graph = new AdjacencyList();
        File testFile = tempDir.resolve("empty.txt").toFile();
        testFile.createNewFile();

        GraphFileReader.readFromFile(graph, testFile.getAbsolutePath());
        assertNotNull(graph.toString());
    }

    @Test
    void readNonExistentFileTest() {
        Graph graph = new AdjacencyList();
        GraphFileReader.readFromFile(graph, "nonexistent.txt");
        assertNotNull(graph);
    }

}
