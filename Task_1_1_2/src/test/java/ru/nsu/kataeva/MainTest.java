package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Test for main class.
 */
class MainTest {

    @Test
    void mainTest() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOutput = System.out;
        System.setOut(new PrintStream(output));

        ByteArrayInputStream input = new ByteArrayInputStream("n".getBytes());
        System.setIn(input);

        try {
            Main.main(new String[]{});

            String out = output.toString();
            assertTrue(out.contains("Welcome to BlackJack!"),
                    "Output should contain welcome message. Actual output: " + out);

        } catch (Exception e) {
            System.err.println("Error during test: " + e.getMessage());
        } finally {
            System.setOut(originalOutput);
            System.setIn(System.in);
        }
    }
}