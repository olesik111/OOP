package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

/**
 * Graph tests.
 */
class GraphTest {

    @ParameterizedTest
    @ArgumentsSource(GraphProvider.class)
    void testAddVertex(Graph<String> graph) {
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("1");

        assertTrue(graph.getNeighbors("1").isEmpty());
        assertTrue(graph.getNeighbors("2").isEmpty());
    }

    @ParameterizedTest
    @ArgumentsSource(GraphProvider.class)
    void testRemoveVertex(Graph<String> graph) {
        graph.addEdge("1", "2");
        graph.addEdge("2", "3");
        graph.addEdge("3", "1");

        graph.removeVertex("2");

        assertFalse(graph.getNeighbors("1").contains("2"));
        assertTrue(graph.getNeighbors("3").contains("1"));
    }

    @ParameterizedTest
    @ArgumentsSource(GraphProvider.class)
    void testAddEdge(Graph<String> graph) {
        graph.addEdge("1", "2");
        graph.addEdge("1", "3");
        graph.addEdge("2", "3");

        assertEquals(List.of("2", "3"), graph.getNeighbors("1"));
        assertEquals(List.of("3"), graph.getNeighbors("2"));
        assertTrue(graph.getNeighbors("3").isEmpty());
    }

    @ParameterizedTest
    @ArgumentsSource(GraphProvider.class)
    void testRemoveEdge(Graph<String> graph) {
        graph.addEdge("1", "2");
        graph.addEdge("1", "3");

        graph.removeEdge("1", "2");

        assertEquals(List.of("3"), graph.getNeighbors("1"));
        assertTrue(graph.getNeighbors("2").isEmpty());
    }

    @ParameterizedTest
    @ArgumentsSource(GraphProvider.class)
    void testGetNeighbors(Graph<String> graph) {
        graph.addEdge("1", "2");
        graph.addEdge("1", "3");
        graph.addEdge("2", "4");

        assertEquals(List.of("2", "3"), graph.getNeighbors("1"));
        assertEquals(List.of("4"), graph.getNeighbors("2"));
        assertTrue(graph.getNeighbors("5").isEmpty());
    }

    @ParameterizedTest
    @ArgumentsSource(GraphProvider.class)
    void testTopologicalSort(Graph<String> graph) {
        graph.addEdge("1", "2");
        graph.addEdge("2", "3");
        graph.addEdge("3", "4");

        List<String> result = graph.topologicalSort();
        assertTrue(result.indexOf("2") < result.indexOf("4"));
        assertTrue(result.indexOf("3") < result.indexOf("4"));
    }

    @ParameterizedTest
    @ArgumentsSource(GraphProvider.class)
    void testEquals(Graph<String> graph) {
        graph.addEdge("1", "2");
        graph.addEdge("1", "3");

        Graph<String> copyGraph;
        if (graph instanceof AdjacencyList) {
            copyGraph = new AdjacencyList<>();
        } else if (graph instanceof AdjacencyMatrix) {
            copyGraph = new AdjacencyMatrix<>();
        } else {
            copyGraph = new IncidenceMatrix<>();
        }

        copyGraph.addEdge("1", "2");
        copyGraph.addEdge("1", "3");

        Graph<String> differentGraph;
        if (graph instanceof AdjacencyList) {
            differentGraph = new AdjacencyList<>();
        } else if (graph instanceof AdjacencyMatrix) {
            differentGraph = new AdjacencyMatrix<>();
        } else {
            differentGraph = new IncidenceMatrix<>();
        }
        differentGraph.addEdge("1", "2");

        assertEquals(graph, copyGraph);
        assertNotEquals(graph, differentGraph);
        assertNotEquals(null, graph);
        assertEquals(graph, graph);
    }

    @ParameterizedTest
    @ArgumentsSource(GraphProvider.class)
    void testToString(Graph<String> graph) {
        graph.addEdge("2", "1");
        graph.addEdge("1", "3");

        String result = graph.toString();
        assertNotNull(result);
        assertTrue(result.contains("1"));
        assertTrue(result.contains("2"));
        assertTrue(result.contains("3"));
    }

    @ParameterizedTest
    @ArgumentsSource(GraphProviderNew.class)
    void testGenericTypes(
            Graph<String> stringGraph, Graph<Integer> integerGraph) {

        stringGraph.addEdge("1", "2");
        stringGraph.addEdge("1", "3");

        integerGraph.addEdge(1, 2);
        integerGraph.addEdge(1, 3);

        assertNotEquals(stringGraph, integerGraph);
        assertNotEquals(integerGraph, stringGraph);
    }

    static class GraphProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new AdjacencyList<String>()),
                    Arguments.of(new AdjacencyMatrix<String>()),
                    Arguments.of(new IncidenceMatrix<String>())
            );
        }
    }

    static class GraphProviderNew implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new AdjacencyList<String>(), new AdjacencyList<Integer>()),
                    Arguments.of(new AdjacencyMatrix<String>(), new AdjacencyMatrix<Integer>()),
                    Arguments.of(new IncidenceMatrix<String>(), new IncidenceMatrix<Integer>())
            );
        }
    }
}
