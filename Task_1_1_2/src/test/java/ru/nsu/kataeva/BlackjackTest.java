package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BlackjackTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testBlackjackConstructor() {
        String input = "n\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Blackjack blackjack = new Blackjack();

        assertNotNull(blackjack);
        assertEquals(0, blackjack.playFlag);
        assertNotNull(blackjack.deckForGame);
        assertNotNull(blackjack.deckForPlayer);
        assertNotNull(blackjack.deckForDealer);
        assertEquals(0, blackjack.winPlayer);
        assertEquals(0, blackjack.winDealer);
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