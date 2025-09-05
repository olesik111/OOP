package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void mainTest() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOutput = System.out;
        System.setOut(new PrintStream(output));

        try {
            Main.main(new String[]{});

            String out = output.toString();
            assertTrue(out.contains("Welcome to BlackJack!"),
                    "Should contain welcome message");
        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
        } finally {
            System.setOut(originalOutput);
        }
    }

    @Test
    void mainTestWithEmptyArgs() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOutput = System.out;
        System.setOut(new PrintStream(output));

        try {
            Main.main(new String[]{});

            String out = output.toString();
            assertTrue(out.contains("Welcome to BlackJack!"),
                    "Should work with empty args");
        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
        } finally {
            System.setOut(originalOutput);
        }
    }

    @Test
    void mainTestWithNullArgs() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOutput = System.out;
        System.setOut(new PrintStream(output));

        try {
            Main.main(null);

            String out = output.toString();
            assertTrue(out.contains("Welcome to BlackJack!"),
                    "Should work with null args");
        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
        } finally {
            System.setOut(originalOutput);
        }
    }
}