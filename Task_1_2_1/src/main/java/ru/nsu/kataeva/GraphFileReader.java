package ru.nsu.kataeva;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Utility class for reading graph data from files.
 */
public class GraphFileReader {
    /**
     * Reads graph edges from file and adds them to graph.
     *
     * @param graph    the graph to populate
     * @param filename the file to read from
     */
    public static void readFromFile(Graph<String> graph, String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNext()) {
                String from = scanner.next();
                String to = scanner.next();
                graph.addEdge(from, to);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }
}