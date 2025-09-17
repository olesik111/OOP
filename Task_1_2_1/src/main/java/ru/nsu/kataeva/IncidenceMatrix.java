package ru.nsu.kataeva;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Graph implementation using incidence matrix representation.
 */
public class IncidenceMatrix implements Graph {
    private final List<int[]> edges;
    private final Set<Integer> vertices;

    /**
     * Creates an empty incidence matrix graph.
     */
    public IncidenceMatrix() {
        edges = new ArrayList<>();
        vertices = new HashSet<>();
    }

    /**
     * Adds a vertex to the graph.
     * @param vertex the vertex to add
     */
    @Override
    public void addVertex(int vertex) {
        vertices.add(vertex);
    }

    /**
     * Removes a vertex and all edges connected to it.
     * @param vertex the vertex to remove
     */
    @Override
    public void removeVertex(int vertex) {
        vertices.remove(vertex);

        List<int[]> edgesToRemove = new ArrayList<>();

        for (int[] edge : edges) {
            if (edge[0] == vertex || edge[1] == vertex) {
                edgesToRemove.add(edge);
            }
        }

        edges.removeAll(edgesToRemove);
    }

    /**
     * Adds a directed edge between two vertices.
     * @param from the source vertex
     * @param to the target vertex
     */
    @Override
    public void addEdge(int from, int to) {
        vertices.add(from);
        vertices.add(to);
        edges.add(new int[]{from, to});
    }

    /**
     * Removes edge between two vertices.
     * @param from the source vertex
     * @param to the target vertex
     */
    @Override
    public void removeEdge(int from, int to) {
        List<int[]> edgesToRemove = new ArrayList<>();

        for (int[] edge : edges) {
            if (edge[0] == from && edge[1] == to) {
                edgesToRemove.add(edge);
            }
        }

        edges.removeAll(edgesToRemove);
    }

    /**
     * Gets all neighbors of a vertex.
     * @param vertex the vertex to check
     * @return list of neighboring vertices
     */
    @Override
    public List<Integer> getNeighbors(int vertex) {
        List<Integer> neighbors = new ArrayList<>();
        for (int[] edge : edges) {
            if (edge[0] == vertex) {
                neighbors.add(edge[1]);
            }
        }
        return neighbors;
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
        IncidenceMatrix other = (IncidenceMatrix) obj;

        if (!vertices.equals(other.vertices)) return false;
        if (edges.size() != other.edges.size()) return false;

        List<int[]> sortedEdges = new ArrayList<>(edges);
        List<int[]> otherSortedEdges = new ArrayList<>(other.edges);

        sortedEdges.sort((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        otherSortedEdges.sort((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        for (int i = 0; i < sortedEdges.size(); i++) {
            int[] edge1 = sortedEdges.get(i);
            int[] edge2 = otherSortedEdges.get(i);
            if (edge1[0] != edge2[0] || edge1[1] != edge2[1]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns string representation of the graph.
     * @return formatted vertices and edges
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IncidenceMatrixGraph:\n");
        sb.append("Vertices: ").append(vertices).append("\n");
        sb.append("Edges:\n");

        List<int[]> sortedEdges = new ArrayList<>(edges);
        sortedEdges.sort((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        for (int[] edge : sortedEdges) {
            sb.append(String.format("  %d -> %d\n", edge[0], edge[1]));
        }

        return sb.toString();
    }
}