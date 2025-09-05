package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final java.io.InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void testMainWithImmediateExit() throws InterruptedException {
        String input = "n\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Thread gameThread = new Thread(() -> {
            try {
                Main.main(new String[]{});
            } catch (Exception ignored) {

            }
        });
        gameThread.start();

        gameThread.join(2000);

        String output = outputStream.toString();
        assertTrue(output.contains("Welcome to BlackJack!"));
        assertTrue(output.contains("New round? y/n"));
        assertTrue(output.contains("Good game!"));
    }

    @Test
    void testMainWithOneRoundAndExit() throws InterruptedException {
        String input = "y\n0\nn\nn\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Thread gameThread = new Thread(() -> {
            try {
                Main.main(new String[]{});
            } catch (Exception ignored) {

            }
        });
        gameThread.start();

        gameThread.join(3000);

        String output = outputStream.toString();
        assertTrue(output.contains("Welcome to BlackJack!"));
        assertTrue(output.contains("Your hand:"));
    }

    @Test
    void testMainWithMultipleRounds() throws InterruptedException {
        String input = "y\n0\nn\ny\n0\nn\nn\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Thread gameThread = new Thread(() -> {
            try {
                Main.main(new String[]{});
            } catch (Exception ignored) {

            }
        });
        gameThread.start();

        gameThread.join(5000);

        String output = outputStream.toString();
        assertTrue(output.contains("Welcome to BlackJack!"));
        assertTrue(output.contains("New round? y/n"));
    }
}