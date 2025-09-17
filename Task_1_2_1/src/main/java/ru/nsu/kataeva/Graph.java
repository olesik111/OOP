package ru.nsu.kataeva;

import java.util.List;

/**
 * Interface for graph data structures.
 * Defines basic graph operations and algorithms.
 */
public interface Graph {

    /**
     * Adds a vertex to the graph.
     *
     * @param vertex the vertex to add
     */
    void addVertex(int vertex);

    /**
     * Removes a vertex from the graph.
     *
     * @param vertex the vertex to remove
     */
    void removeVertex(int vertex);

    /**
     * Adds a directed edge between two vertices.
     *
     * @param from the source vertex
     * @param to the target vertex
     */
    void addEdge(int from, int to);

    /**
     * Removes edge between two vertices.
     *
     * @param from the source vertex
     * @param to the target vertex
     */
    void removeEdge(int from, int to);

    /**
     * Gets all neighbors of a vertex.
     *
     * @param vertex the vertex to check
     * @return list of neighboring vertices
     */
    List<Integer> getNeighbors(int vertex);

    /**
     * Performs topological sorting of the graph.
     *
     * @return vertices in topological order
     */
    List<Integer> topologicalSort();

    /**
     * Compares two graphs for equality.
     *
     * @param obj the graph to compare with
     * @return true if graphs are equal
     */
    boolean equals(Object obj);

    /**
     * Returns string representation of the graph.
     *
     * @return formatted graph structure
     */
    String toString();
}