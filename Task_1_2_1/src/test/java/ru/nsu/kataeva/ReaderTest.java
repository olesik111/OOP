package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * ReaderTest.
 */
class ReaderTest {

    @TempDir
    Path tempDir;

    @Test
    void readValidGraphTest() throws Exception {
        Graph<String> graph = new AdjacencyList<>();
        File testFile = tempDir.resolve("graph.txt").toFile();

        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("1 2\n");
            writer.write("2 3\n");
        }

        GraphFileReader.readFromFile(graph, testFile.getAbsolutePath());
        assertNotNull(graph.toString());
    }

    @Test
    void readEmptyFileTest() throws Exception {
        Graph<String> graph = new AdjacencyList<>();
        File testFile = tempDir.resolve("empty.txt").toFile();
        testFile.createNewFile();

        GraphFileReader.readFromFile(graph, testFile.getAbsolutePath());
        assertNotNull(graph.toString());
    }

    @Test
    void readNonExistentFileTest() {
        Graph<String> graph = new AdjacencyList<>();
        assertThrows(
                java.io.FileNotFoundException.class,
                () -> GraphFileReader.readFromFile(graph, "nonexistent.txt")
        );
    }

}