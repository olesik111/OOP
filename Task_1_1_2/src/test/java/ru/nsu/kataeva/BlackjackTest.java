package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlackJackTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    void testBlackjackConstructor() {
        Blackjack game = new Blackjack();
        assertEquals(0, game.winPlayer);
        assertEquals(0, game.winDealer);
        assertNotNull(game.deckForGame);
    }

    @Test
    void testGameEndsOnN() {
        provideInput("n");
        Blackjack game = new Blackjack();
        game.game();
        assertTrue(testOut.toString().contains("Good game!"));
        assertEquals(0, game.playFlag);
    }

    @Test
    void testGameWithInvalidInputThenExit() {
        provideInput("invalid\nn");
        Blackjack game = new Blackjack();
        game.game();
        String output = testOut.toString();
        assertTrue(output.contains("Please answer y/n"));
        assertTrue(output.contains("Good game!"));
    }

}