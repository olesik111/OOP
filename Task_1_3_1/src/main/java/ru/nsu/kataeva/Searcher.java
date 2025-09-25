package ru.nsu.kataeva;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Search substring realization.
 */
public class Searcher {
    private final String file;
    private final String pattern;

    /**
     * Constructor.
     *
     * @param file    file to check.
     * @param pattern pattern to find.
     */
    public Searcher(String file, String pattern) {
        this.file = file;
        this.pattern = pattern;
    }

    /**
     * Method to find matches with the help of buffer reading.
     *
     * @return the list if indices.
     * @throws IOException if an error.
     */
    public List<Long> findMatch() throws IOException {
        List<Long> ind = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file),
                        StandardCharsets.UTF_8))) {
            char[] buffer = new char[4096];
            int read;
            long fileInd = 0;
            String tail = "";

            while ((read = reader.read(buffer)) != -1) {
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
        }
        return ind;
    }
}
