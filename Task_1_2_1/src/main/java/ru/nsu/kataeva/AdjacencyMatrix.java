package ru.nsu.kataeva;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph implementation using adjacency matrix representation.
 */
public class AdjacencyMatrix implements Graph {
    private int[][] matrix;
    private int vertexCount;

    /**
     * Creates an adjacency matrix with initial size 10x10.
     */
    public AdjacencyMatrix() {
        matrix = new int[10][10];
    }

    /**
     * Ensures matrix can store up to given vertex index.
     *
     * @param vertex the vertex index to accommodate
     */
    private void ensureCapacity(int vertex) {
        if (vertex >= matrix.length) {
            int newSize = Math.max(vertex + 1, matrix.length * 2);
            int[][] newMatrix = new int[newSize][newSize];
            for (int i = 0; i < matrix.length; i++) {
                System.arraycopy(matrix[i], 0, newMatrix[i], 0, matrix.length);
            }
            matrix = newMatrix;
        }
    }

    /**
     * Adds a vertex to the graph.
     *
     * @param vertex the vertex to add
     */
    @Override
    public void addVertex(int vertex) {
        ensureCapacity(vertex);
        vertexCount = Math.max(vertexCount, vertex + 1);
    }

    /**
     * Removes a vertex and all its edges.
     *
     * @param vertex the vertex to remove
     */
    @Override
    public void removeVertex(int vertex) {
        if (vertex < matrix.length) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[vertex][i] = 0;
                matrix[i][vertex] = 0;
            }
        }
    }

    /**
     * Adds a directed edge between two vertices.
     *
     * @param from the source vertex
     * @param to the target vertex
     */
    @Override
    public void addEdge(int from, int to) {
        ensureCapacity(Math.max(from, to));
        matrix[from][to] = 1;
        vertexCount = Math.max(vertexCount, Math.max(from, to) + 1);
    }

    /**
     * Removes edge between two vertices.
     *
     * @param from the source vertex
     * @param to the target vertex
     */
    @Override
    public void removeEdge(int from, int to) {
        if (from < matrix.length && to < matrix.length) {
            matrix[from][to] = 0;
        }
    }

    /**
     * Gets all neighbors of a vertex.
     *
     * @param vertex the vertex to check
     * @return list of neighboring vertices
     */
    @Override
    public List<Integer> getNeighbors(int vertex) {
        List<Integer> neighbors = new ArrayList<>();
        if (vertex < matrix.length) {
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[vertex][i] == 1) {
                    neighbors.add(i);
                }
            }
        }
        return neighbors;
    }

    /**
     * Performs topological sorting of the graph.
     *
     * @return vertices in topological order
     */
    @Override
    public List<Integer> topologicalSort() {
        return GraphTopologicalSort.topologicalSort(this);
    }

    /**
     * Compares two graphs for equality.
     *
     * @param obj the graph to compare with
     * @return true if graphs have same edges
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AdjacencyMatrix other = (AdjacencyMatrix) obj;

        int maxSize = Math.max(matrix.length, other.matrix.length);
        for (int i = 0; i < maxSize; i++) {
            for (int j = 0; j < maxSize; j++) {
                int thisVal = (i < matrix.length && j < matrix.length) ? matrix[i][j] : 0;
                int otherVal = (i < other.matrix.length && j < other.matrix.length)
                        ? other.matrix[i][j] : 0;
                if (thisVal != otherVal) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns string representation of the matrix.
     *
     * @return formatted matrix with row/column headers
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AdjacencyMatrixGraph:\n");
        int size = matrix.length;

        sb.append("   ");
        for (int i = 0; i < size; i++) {
            sb.append(String.format("%2d ", i));
        }
        sb.append("\n");

        for (int i = 0; i < size; i++) {
            sb.append(String.format("%2d ", i));
            for (int j = 0; j < size; j++) {
                sb.append(String.format("%2d ", matrix[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}