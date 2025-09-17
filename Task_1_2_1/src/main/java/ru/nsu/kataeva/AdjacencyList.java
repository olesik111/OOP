package ru.nsu.kataeva;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Graph implementation using adjacency list representation.
 */
public class AdjacencyList implements Graph {
    private final Map<Integer, List<Integer>> adjList;

    /**
     * Creates an empty adjacency list graph.
     */
    public AdjacencyList() {
        adjList = new HashMap<>();
    }

    /**
     * Adds a vertex to the graph if it doesn't exist.
     * @param vertex the vertex to add
     */
    @Override
    public void addVertex(int vertex) {
        if (!adjList.containsKey(vertex)) {
            adjList.put(vertex, new ArrayList<>());
        }
    }

    /**
     * Removes a vertex and all edges connected to it.
     * @param vertex the vertex to remove
     */
    @Override
    public void removeVertex(int vertex) {
        adjList.remove(vertex);

        for (List<Integer> neighbors : adjList.values()) {
            List<Integer> toRemove = new ArrayList<>();
            for (Integer n : neighbors) {
                if (n == vertex) {
                    toRemove.add(n);
                }
            }
            neighbors.removeAll(toRemove);
        }
    }

    /**
     * Adds a directed edge between two vertices.
     * Creates vertices if they don't exist.
     * @param from the source vertex
     * @param to the target vertex
     */
    @Override
    public void addEdge(int from, int to) {
        addVertex(from);
        addVertex(to);
        adjList.get(from).add(to);
    }

    /**
     * Removes all edges from source to target vertex.
     * @param from the source vertex
     * @param to the target vertex
     */
    @Override
    public void removeEdge(int from, int to) {
        if (adjList.containsKey(from)) {
            List<Integer> neighbors = adjList.get(from);
            List<Integer> toRemove = new ArrayList<>();
            for (Integer n : neighbors) {
                if (n == to) {
                    toRemove.add(n);
                }
            }
            neighbors.removeAll(toRemove);
        }
    }

    /**
     * Gets all neighbors of a vertex.
     * @param vertex the vertex to check
     * @return list of neighboring vertices
     */
    @Override
    public List<Integer> getNeighbors(int vertex) {
        if (adjList.containsKey(vertex)) {
            return adjList.get(vertex);
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Performs topological sorting of the graph.
     * @return vertices in topological order
     */
    @Override
    public List<Integer> topologicalSort() {
        return GraphTopologicalSort.topologicalSort(this);
    }

    /**
     * Compares two graphs for equality.
     * @param obj the graph to compare with
     * @return true if graphs have same vertices and edges
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AdjacencyList other = (AdjacencyList) obj;

        if (adjList.size() != other.adjList.size()) return false;

        for (Map.Entry<Integer, List<Integer>> entry : adjList.entrySet()) {
            int vertex = entry.getKey();
            List<Integer> neighbors = entry.getValue();

            if (!other.adjList.containsKey(vertex)) return false;

            List<Integer> otherNeighbors = other.adjList.get(vertex);
            if (neighbors.size() != otherNeighbors.size()) return false;

            Collections.sort(neighbors);
            Collections.sort(otherNeighbors);
            if (!neighbors.equals(otherNeighbors)) return false;
        }

        return true;
    }

    /**
     * Returns string representation of the graph.
     * @return formatted graph structure
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AdjacencyListGraph:\n");

        List<Integer> vertices = new ArrayList<>(adjList.keySet());
        Collections.sort(vertices);

        for (int vertex : vertices) {
            List<Integer> neighbors = new ArrayList<>(adjList.get(vertex));
            Collections.sort(neighbors);
            sb.append(String.format("%d -> %s\n", vertex, neighbors));
        }

        return sb.toString();
    }
}