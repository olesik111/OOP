package ru.nsu.kataeva;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
     * @param graph the graph to sort
     * @return vertices in topological order
     * @throws IllegalArgumentException if graph contains cycles
     */
    public static List<Integer> topologicalSort(Graph graph) {
        Set<Integer> allVertices = getAllVertices(graph);

        Map<Integer, Integer> inDegree = new HashMap<>();
        for (int vertex : allVertices) {
            inDegree.put(vertex, 0);
        }

        for (int vertex : allVertices) {
            for (int neighbor : graph.getNeighbors(vertex)) {
                inDegree.put(neighbor, inDegree.get(neighbor) + 1);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int vertex : allVertices) {
            if (inDegree.get(vertex) == 0) {
                queue.add(vertex);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result.add(vertex);

            for (int neighbor : graph.getNeighbors(vertex)) {
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
     * @param graph the graph to analyze
     * @return set of all vertices
     */
    private static Set<Integer> getAllVertices(Graph graph) {
        Set<Integer> vertices = new HashSet<>();

        for (int i = 0; i <= findMaxPossibleVertex(graph); i++) {
            List<Integer> neighbors = graph.getNeighbors(i);
            if (!neighbors.isEmpty()) {
                vertices.add(i);
                vertices.addAll(neighbors);
            }
        }

        for (int i = 0; i <= findMaxPossibleVertex(graph); i++) {
            if (hasIncomingEdges(graph, i)) {
                vertices.add(i);
            }
        }

        return vertices;
    }

    /**
     * Finds the maximum possible vertex index by checking neighbors.
     * @param graph the graph to analyze
     * @return maximum vertex index found
     */
    private static int findMaxPossibleVertex(Graph graph) {
        int max = 0;
        for (int i = 0; i < 1000; i++) {
            List<Integer> neighbors = graph.getNeighbors(i);
            if (!neighbors.isEmpty()) {
                for (int neighbor : neighbors) {
                    max = Math.max(max, neighbor);
                }
                max = Math.max(max, i);
            } else if (hasIncomingEdges(graph, i)) {
                max = Math.max(max, i);
            }
        }
        return max;
    }

    /**
     * Checks if a vertex has any incoming edges.
     * @param graph the graph to check
     * @param vertex the vertex to check
     * @return true if vertex has incoming edges
     */
    private static boolean hasIncomingEdges(Graph graph, int vertex) {
        for (int i = 0; i < 1000; i++) {
            if (graph.getNeighbors(i).contains(vertex)) {
                return true;
            }
        }
        return false;
    }
}