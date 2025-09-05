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
                    "hello message");
        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
        } finally {
            System.setOut(originalOutput);
        }
    }
}