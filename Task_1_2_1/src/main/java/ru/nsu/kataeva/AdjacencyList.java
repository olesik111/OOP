package ru.nsu.kataeva;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Graph implementation using adjacency list representation.
 */
public class AdjacencyList<T> implements Graph<T> {
    private final Map<T, List<T>> adjList;

    /**
     * Creates an empty adjacency list graph.
     */
    public AdjacencyList() {
        adjList = new HashMap<>();
    }

    /**
     * Adds a vertex to the graph if it doesn't exist.
     *
     * @param vertex the vertex to add
     */
    @Override
    public void addVertex(T vertex) {
        if (!adjList.containsKey(vertex)) {
            adjList.put(vertex, new ArrayList<>());
        }
    }

    /**
     * Removes a vertex and all edges connected to it.
     *
     * @param vertex the vertex to remove
     */
    @Override
    public void removeVertex(T vertex) {
        adjList.remove(vertex);
        for (List<T> neighbors : adjList.values()) {
            neighbors.removeIf(v -> v.equals(vertex));
        }
    }

    /**
     * Adds a directed edge between two vertices.
     * Creates vertices if they don't exist.
     *
     * @param from the source vertex
     * @param to   the target vertex
     */
    @Override
    public void addEdge(T from, T to) {
        addVertex(from);
        addVertex(to);
        adjList.get(from).add(to);
    }

    /**
     * Removes all edges from source to target vertex.
     *
     * @param from the source vertex
     * @param to   the target vertex
     */
    @Override
    public void removeEdge(T from, T to) {
        if (adjList.containsKey(from)) {
            List<T> neighbors = adjList.get(from);
            neighbors.removeIf(n -> n.equals(to));
        }
    }

    /**
     * Gets all neighbors of a vertex.
     *
     * @param vertex the vertex to check
     * @return list of neighboring vertices
     */
    @Override
    public List<T> getNeighbors(T vertex) {
        return adjList.getOrDefault(vertex, new ArrayList<>());
    }

    /**
     * Performs topological sorting of the graph.
     *
     * @return vertices in topological order
     */
    @Override
    public List<T> topologicalSort() {
        return GraphTopologicalSort.topologicalSort(this);
    }

    /**
     * Returns all vertices of the graph.
     *
     * @return set of vertices
     */
    @Override
    public Set<T> getVertices() {
        return new HashSet<>(adjList.keySet());
    }

    /**
     * Compares two graphs for equality.
     *
     * @param obj the graph to compare with
     * @return true if graphs have same vertices and edges
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        AdjacencyList<?> other = (AdjacencyList<?>) obj;

        if (adjList.size() != other.adjList.size()) {
            return false;
        }

        for (Map.Entry<T, List<T>> entry : adjList.entrySet()) {
            T vertex = entry.getKey();
            List<T> neighbors = entry.getValue();

            if (!other.adjList.containsKey(vertex)) {
                return false;
            }

            List<?> otherNeighbors = other.adjList.get(vertex);
            if (neighbors.size() != otherNeighbors.size()) {
                return false;
            }

            if (!new HashSet<>(neighbors).equals(new HashSet<>(otherNeighbors))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns hash code for the graph.
     * Throws exception as hash code implementation for graphs is complex
     * and not needed for current use cases.
     *
     * @return hash code value
     * @throws UnsupportedOperationException if called
     */
    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Hash code not implemented for Graph objects");
    }

    /**
     * Returns string representation of the graph.
     *
     * @return formatted graph structure
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AdjacencyListGraph:\n");

        List<T> vertices = new ArrayList<>(adjList.keySet());
        vertices.sort(Comparator.comparing(Object::toString));

        for (T vertex : vertices) {
            List<T> neighbors = new ArrayList<>(adjList.get(vertex));
            neighbors.sort(Comparator.comparing(Object::toString));
            sb.append(String.format("%s -> %s\n", vertex, neighbors));
        }
        return sb.toString();
    }
}
