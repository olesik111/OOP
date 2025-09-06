package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlackjackTest {
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

        assertDoesNotThrow(Blackjack::new);

        String output = outputStream.toString();
        assertTrue(output.contains("New round?"));

        System.setIn(System.in);
    }
}