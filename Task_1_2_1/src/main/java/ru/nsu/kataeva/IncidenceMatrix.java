package ru.nsu.kataeva;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Graph implementation using incidence matrix representation.
 */
public class IncidenceMatrix<T> implements Graph<T> {
    private final List<Edge<T>> edges;
    private final Set<T> vertices;

    /**
     * Internal class to represent an edge.
     */
    private static class Edge<T> {
        T from;
        T to;

        Edge(T from, T to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Edge<?> other)) {
                return false;
            }
            return Objects.equals(from, other.from) && Objects.equals(to, other.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }

    /**
     * Creates an empty incidence matrix graph.
     */
    public IncidenceMatrix() {
        edges = new ArrayList<>();
        vertices = new HashSet<>();
    }

    @Override
    public void addVertex(T vertex) {
        if (vertex != null) {
            vertices.add(vertex);
        }
    }

    @Override
    public void removeVertex(T vertex) {
        if (vertex == null || !vertices.contains(vertex)) {
            return;
        }
        vertices.remove(vertex);
        edges.removeIf(edge -> edge.from.equals(vertex) || edge.to.equals(vertex));
    }

    @Override
    public void addEdge(T from, T to) {
        if (from == null || to == null) {
            return;
        }
        vertices.add(from);
        vertices.add(to);
        edges.add(new Edge<>(from, to));
    }

    @Override
    public void removeEdge(T from, T to) {
        if (from == null || to == null) {
            return;
        }
        edges.removeIf(edge -> edge.from.equals(from) && edge.to.equals(to));
    }

    @Override
    public List<T> getNeighbors(T vertex) {
        List<T> neighbors = new ArrayList<>();
        if (vertex == null) {
            return neighbors;
        }
        for (Edge<T> edge : edges) {
            if (edge.from.equals(vertex)) {
                neighbors.add(edge.to);
            }
        }
        return neighbors;
    }

    @Override
    public List<T> topologicalSort() {
        return GraphTopologicalSort.topologicalSort(this);
    }

    @Override
    public Set<T> getVertices() {
        return new HashSet<>(vertices);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IncidenceMatrix<?> other)) {
            return false;
        }

        if (!vertices.equals(other.vertices)) {
            return false;
        }
        if (edges.size() != other.edges.size()) {
            return false;
        }

        return new HashSet<>(edges).equals(new HashSet<>(other.edges));
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Hash code not implemented for Graph objects");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IncidenceMatrixGraph:\n");

        List<T> sortedVertices = new ArrayList<>(vertices);
        sortedVertices.sort(Comparator.comparing(Object::toString));
        sb.append("Vertices: ").append(sortedVertices).append("\n");

        sb.append("Edges:\n");
        List<Edge<T>> sortedEdges = new ArrayList<>(edges);
        sortedEdges.sort(Comparator.comparing((Edge<T> a) -> a.from.toString())
                .thenComparing(a -> a.to.toString()));

        for (Edge<T> edge : sortedEdges) {
            sb.append(String.format("  %s -> %s%n", edge.from, edge.to));
        }

        return sb.toString();
    }
}
