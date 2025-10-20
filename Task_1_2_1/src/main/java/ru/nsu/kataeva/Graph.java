package ru.nsu.kataeva;

import java.util.List;
import java.util.Set;

/**
 * Interface for graph data structures.
 * Defines basic graph operations and algorithms.
 */
public interface Graph<T> {

    /**
     * Adds a vertex to the graph.
     *
     * @param vertex the vertex to add
     */
    void addVertex(T vertex);

    /**
     * Removes a vertex from the graph.
     *
     * @param vertex the vertex to remove
     */
    void removeVertex(T vertex);

    /**
     * Adds a directed edge between two vertices.
     *
     * @param from the source vertex
     * @param to   the target vertex
     */
    void addEdge(T from, T to);

    /**
     * Removes edge between two vertices.
     *
     * @param from the source vertex
     * @param to   the target vertex
     */
    void removeEdge(T from, T to);

    /**
     * Gets all neighbors of a vertex.
     *
     * @param vertex the vertex to check
     * @return list of neighboring vertices
     */
    List<T> getNeighbors(T vertex);

    /**
     * Performs topological sorting of the graph.
     *
     * @return vertices in topological order
     */
    List<T> topologicalSort();

    Set<T> getVertices();

}