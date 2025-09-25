package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Searcher tests.
 */
public class SearcherTest {

    @TempDir
    Path tempDir;

    private File createTempFile(String content) throws IOException {
        File file = tempDir.resolve("test.txt").toFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        }
        return file;
    }

    @Test
    void testSimpleMatch() throws IOException {
        File file = createTempFile("abc abc abc");
        Searcher searcher = new Searcher(file.getAbsolutePath(), "abc");
        final List<Long> matches = searcher.findMatch();

        ArrayList<Long> list = new ArrayList<>();
        list.add((long) 0);
        list.add((long) 4);
        list.add((long) 8);

        assertEquals(list, matches);
    }

    @Test
    void testSimpleMatchRus() throws IOException {
        File file = createTempFile("бла бле бло");
        Searcher searcher = new Searcher(file.getAbsolutePath(), "бл");
        final List<Long> matches = searcher.findMatch();

        ArrayList<Long> list = new ArrayList<>();
        list.add((long) 0);
        list.add((long) 4);
        list.add((long) 8);

        assertEquals(list, matches);
    }

    @Test
    void testNoMatch() throws IOException {
        File file = createTempFile("abcdefgh");
        Searcher searcher = new Searcher(file.getAbsolutePath(), "z");
        List<Long> matches = searcher.findMatch();

        assertEquals(List.of(), matches);
    }

    @Test
    void testBoundary() throws IOException {
        String sb = "a".repeat(4095)
                +
                "abc";
        File file = createTempFile(sb);

        Searcher searcher = new Searcher(file.getAbsolutePath(), "abc");
        List<Long> matches = searcher.findMatch();

        ArrayList<Long> list = new ArrayList<>();
        list.add((long) 4095);

        assertEquals(list, matches);
    }

    @Test
    void testLarge() throws IOException {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 100; i++) {
            sb.append("a".repeat(100000));
            sb.append("xy");
        }

        File file = createTempFile(sb.toString());

        Searcher searcher = new Searcher(file.getAbsolutePath(), "xy");
        List<Long> matches = searcher.findMatch();

        assertEquals(100, matches.size());

        assertEquals(100000, matches.get(0));
        assertEquals((100 * 100_000) + (99 * 2), matches.get(99));
    }

}
