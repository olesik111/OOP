package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for ScoreDisplay.
 */
public class ScoreDisplayTest {

    private final PrintStream standardOut = System.out;
    private ByteArrayOutputStream outputStreamCaptor;

    @BeforeEach
    public void setUp() {
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testDisplayScore() {
        ScoreDisplay.displayScore(3, 2);

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Score: 3:2"));
    }

    @Test
    public void testDisplayYouWin() {
        ScoreDisplay.displayYouWin(4, 1);

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("You won!"));
        assertTrue(output.contains("Score: 4:1"));
    }

    @Test
    public void testDisplayYouLose() {
        ScoreDisplay.displayYouLose(2, 5);

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("You lost!"));
        assertTrue(output.contains("Score: 2:5"));
    }

    @Test
    public void testDisplayDraw() {
        ScoreDisplay.displayDraw(3, 3);

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("It's a draw!"));
        assertTrue(output.contains("Score: 3:3"));
    }

    @Test
    public void testDisplayBust() {
        ScoreDisplay.displayBust(1, 4);

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("You lost! Bust!"));
        assertTrue(output.contains("Score: 1:4"));
    }

    @Test
    public void testDisplayDealerBlackjack() {
        ScoreDisplay.displayDealerBlackjack(0, 2);

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("You lost! Dealer has Blackjack!"));
        assertTrue(output.contains("Score: 0:2"));
    }

    @Test
    public void testDisplayScoreWithZeroValues() {
        ScoreDisplay.displayScore(0, 0);

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Score: 0:0"));
    }

    @Test
    public void testDisplayScoreWithLargeValues() {
        ScoreDisplay.displayScore(99, 100);

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Score: 99:100"));
    }
}