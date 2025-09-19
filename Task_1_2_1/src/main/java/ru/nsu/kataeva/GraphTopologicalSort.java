package ru.nsu.kataeva;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Provides topological sort algorithm for directed acyclic graphs.
 */
public class GraphTopologicalSort {

    /**
     * Performs topological sort on a directed graph.
     *
     * @param graph the graph to sort
     * @return vertices in topological order
     * @throws IllegalArgumentException if graph contains cycles
     */
    public static <T> List<T> topologicalSort(Graph<T> graph) {
        Set<T> allVertices = getAllVertices(graph);

        Map<T, Integer> inDegree = new HashMap<>();
        for (T vertex : allVertices) {
            inDegree.put(vertex, 0);
        }

        for (T vertex : allVertices) {
            for (T neighbor : graph.getNeighbors(vertex)) {
                inDegree.put(neighbor, inDegree.get(neighbor) + 1);
            }
        }

        Queue<T> queue = new LinkedList<>();
        for (T vertex : allVertices) {
            if (inDegree.get(vertex) == 0) {
                queue.add(vertex);
            }
        }

        List<T> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            result.add(vertex);

            for (T neighbor : graph.getNeighbors(vertex)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        if (result.size() != allVertices.size()) {
            throw new IllegalArgumentException("Graph contains cycles");
        }

        return result;
    }

    /**
     * Collects all vertices present in the graph.
     *
     * @param graph the graph to analyze
     * @return set of all vertices
     */
    private static <T> Set<T> getAllVertices(Graph<T> graph) {
        return graph.getVertices();
    }

}