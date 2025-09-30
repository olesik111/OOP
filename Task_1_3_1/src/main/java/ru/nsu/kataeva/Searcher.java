package ru.nsu.kataeva;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Search substring realization.
 */
public class Searcher {
    /**
     * Constructor.
     */
    private Searcher() {
    }

    /**
     Method to find matches in a file.

     @param file file to check
     @param pattern pattern to find
     @return the list of indices
     @throws IllegalArgumentException if an error occurs
     */
    public static List<Long> findMatch(String file, String pattern) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            return findMatch(reader, pattern);
        } catch (IOException e) {
            throw new IllegalArgumentException("Please, check your file or path again." +
                    "Error while reading file: " + file, e);
        }
    }

    /**
     * Method to find matches in any Reader source.
     *
     * @param reader    file to check.
     * @param pattern pattern to find.
     * @return the list if indices.
     * @throws IOException if an error.
     */
    public static List<Long> findMatch(Reader reader, String pattern) throws IOException {
        List<Long> ind = new ArrayList<>();
        BufferedReader br = (reader instanceof BufferedReader)
                ? (BufferedReader) reader
                : new BufferedReader(reader);

        char[] buffer = new char[4096];
        int read;
        long fileInd = 0;
        String tail = "";

        while ((read = br.read(buffer)) != -1) {
            String bite = tail + new String(buffer, 0, read);
            int startInd = 0;

            while (true) {
                int i = bite.indexOf(pattern, startInd);
                if (i < 0) {
                    break;
                }
                startInd = i + 1;
                ind.add(i - tail.length() + fileInd);
            }
            fileInd += read;

            int keep = Math.min(Math.max(pattern.length() - 1, 0), bite.length());
            tail = bite.substring(bite.length() - keep);


        }

        return ind;
    }
}
