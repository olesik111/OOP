package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
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
        file.deleteOnExit();
        return file;
    }

    @Test
    void testSimpleMatch() throws IOException {
        File file = createTempFile("abc abc abc");
        final List<Long> matches = Searcher.findMatch(file.getAbsolutePath(), "abc");

        ArrayList<Long> list = new ArrayList<>();
        list.add(0L);
        list.add(4L);
        list.add(8L);

        assertEquals(list, matches);
    }

    @Test
    void testSimpleMatchRus() throws IOException {
        File file = createTempFile("бла бле бло");
        final List<Long> matches = Searcher.findMatch(file.getAbsolutePath(), "бл");

        ArrayList<Long> list = new ArrayList<>();
        list.add(0L);
        list.add(4L);
        list.add(8L);

        assertEquals(list, matches);
    }

    @Test
    void testNoMatch() throws IOException {
        File file = createTempFile("abcdefgh");
        final List<Long> matches = Searcher.findMatch(file.getAbsolutePath(), "ac");

        assertEquals(List.of(), matches);
    }

    @Test
    void testBoundary() throws IOException {
        String sb = "a".repeat(4095)
                +
                "abc";
        File file = createTempFile(sb);

        final List<Long> matches = Searcher.findMatch(file.getAbsolutePath(), "abc");

        ArrayList<Long> list = new ArrayList<>();
        list.add(4095L);

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

        final List<Long> matches = Searcher.findMatch(file.getAbsolutePath(), "xy");

        assertEquals(100, matches.size());

        assertEquals(100000, matches.get(0));
        assertEquals((100 * 100_000) + (99 * 2), matches.get(99));
    }

    @Test
    void testFromStringReader() throws IOException {
        String input = "hello world, hello!";
        try (Reader reader = new StringReader(input)) {
            final List<Long> matches = Searcher.findMatch(reader, "hello");
            ArrayList<Long> list = new ArrayList<>();
            list.add(0L);
            list.add(13L);

            assertEquals(list, matches);
        }


    }


}
