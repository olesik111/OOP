package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    private Blackjack blackjack;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testBlackjackConstructorInitializesDecks() {
        String input = "n\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        assertDoesNotThrow(() -> {
            Blackjack game = new Blackjack();
            assertNotNull(game);
        });

        System.setIn(System.in);
    }



    @Test
    void testGameWithImmediateQuit() {
        String input = "n\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        assertDoesNotThrow(() -> new Blackjack());

        String output = outputStream.toString();
        assertTrue(output.contains("New round?"));

        System.setIn(System.in);
    }
}