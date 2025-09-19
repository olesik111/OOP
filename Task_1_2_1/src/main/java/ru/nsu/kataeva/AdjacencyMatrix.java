package ru.nsu.kataeva;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdjacencyMatrix<T> implements Graph<T> {
    private final Map<T, Integer> vertexToIndex;
    private final Map<Integer, T> indexToVertex;
    private int[][] matrix;
    private int vertexCount;

    public AdjacencyMatrix() {
        vertexToIndex = new HashMap<>();
        indexToVertex = new HashMap<>();
        matrix = new int[10][10];
        vertexCount = 0;
    }

    private void ensureCapacity(int size) {
        if (size >= matrix.length) {
            int newSize = Math.max(size + 1, matrix.length * 2);
            int[][] newMatrix = new int[newSize][newSize];
            for (int i = 0; i < matrix.length; i++) {
                System.arraycopy(matrix[i], 0, newMatrix[i], 0, matrix.length);
            }
            matrix = newMatrix;
        }
    }

    private int getVertexIndex(T vertex) {
        if (!vertexToIndex.containsKey(vertex)) {
            ensureCapacity(vertexCount);
            vertexToIndex.put(vertex, vertexCount);
            indexToVertex.put(vertexCount, vertex);
            vertexCount++;
        }
        return vertexToIndex.get(vertex);
    }

    private T getVertexFromIndex(int index) {
        return indexToVertex.get(index);
    }

    @Override
    public void addVertex(T vertex) {
        getVertexIndex(vertex);
    }

    @Override
    public void removeVertex(T vertex) {
        if (!vertexToIndex.containsKey(vertex)) {
            return;
        }

        int index = vertexToIndex.get(vertex);
        int lastIndex = vertexCount - 1;

        if (index != lastIndex) {
            int[] lastRow = new int[vertexCount];
            int[] lastCol = new int[vertexCount];
            for (int i = 0; i < vertexCount; i++) {
                lastRow[i] = matrix[lastIndex][i];
                lastCol[i] = matrix[i][lastIndex];
            }

            for (int i = 0; i < vertexCount; i++) {
                matrix[index][i] = lastRow[i];
                matrix[i][index] = lastCol[i];
            }

            T lastVertex = indexToVertex.get(lastIndex);
            vertexToIndex.put(lastVertex, index);
            indexToVertex.put(index, lastVertex);
        }

        for (int i = 0; i < vertexCount; i++) {
            matrix[lastIndex][i] = 0;
            matrix[i][lastIndex] = 0;
        }

        vertexToIndex.remove(vertex);
        indexToVertex.remove(lastIndex);
        vertexCount--;
    }

    @Override
    public void addEdge(T from, T to) {
        int fromIndex = getVertexIndex(from);
        int toIndex = getVertexIndex(to);
        matrix[fromIndex][toIndex] = 1;
    }

    @Override
    public void removeEdge(T from, T to) {
        if (vertexToIndex.containsKey(from) && vertexToIndex.containsKey(to)) {
            int fromIndex = vertexToIndex.get(from);
            int toIndex = vertexToIndex.get(to);
            matrix[fromIndex][toIndex] = 0;
        }
    }

    @Override
    public List<T> getNeighbors(T vertex) {
        List<T> neighbors = new ArrayList<>();
        if (vertexToIndex.containsKey(vertex)) {
            int vertexIndex = vertexToIndex.get(vertex);
            for (int i = 0; i < vertexCount; i++) {
                if (matrix[vertexIndex][i] == 1) {
                    neighbors.add(getVertexFromIndex(i));
                }
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
        return new HashSet<>(vertexToIndex.keySet());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AdjacencyMatrix<?> other)) return false;

        if (!vertexToIndex.keySet().equals(other.vertexToIndex.keySet())) {
            return false;
        }

        @SuppressWarnings("unchecked")
        AdjacencyMatrix<T> castedOther = (AdjacencyMatrix<T>) other;

        for (T from : vertexToIndex.keySet()) {
            List<T> thisNeighbors = getNeighbors(from);
            List<T> otherNeighbors = castedOther.getNeighbors(from);

            if (!new HashSet<>(thisNeighbors).equals(new HashSet<>(otherNeighbors))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Hash code not implemented for Graph objects");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AdjacencyMatrixGraph:\n");

        List<T> vertices = new ArrayList<>(vertexToIndex.keySet());
        vertices.sort(Comparator.comparing(Object::toString));

        sb.append("     ");
        for (T vertex : vertices) {
            sb.append(String.format("%5s ", vertex));
        }
        sb.append("\n");

        for (T from : vertices) {
            sb.append(String.format("%5s ", from));
            int fromIndex = vertexToIndex.get(from);
            for (T to : vertices) {
                int toIndex = vertexToIndex.get(to);
                sb.append(String.format("%5d ", matrix[fromIndex][toIndex]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
