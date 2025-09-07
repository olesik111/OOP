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

/**
 * Blackjack test class.
 */
public class BlackjackTest {

    private final PrintStream standardOut = System.out;
    private final InputStream standardIn = System.in;
    private ByteArrayOutputStream outputStreamCaptor;

    @BeforeEach
    public void setUp() {
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        System.setIn(standardIn);
    }

    @Test
    public void testMainMethod() {
        String input = "n\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Blackjack.main(new String[]{});

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Welcome to BlackJack!"));
        assertTrue(output.contains("New round?"));
        assertTrue(output.contains("Good game!"));
    }

    @Test
    public void testConstructor() {
        Blackjack blackjack = new Blackjack();

        assertNotNull(blackjack.deckForGame);
        assertNotNull(blackjack.deckForPlayer);
        assertNotNull(blackjack.deckForDealer);
        assertEquals(0, blackjack.winPlayer);
        assertEquals(0, blackjack.winDealer);
    }

    @Test
    void testGameStartAndFinish() {
        String input = "n\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Blackjack blackjack = new Blackjack();
        blackjack.game();

        String output = outputStreamCaptor.toString();

        assertTrue(output.contains("New round?"));
        assertTrue(output.contains("Good game!"));
    }

    @Test
    void testGameInvalidInput() {
        String input = "invalid\nn\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Blackjack blackjack = new Blackjack();
        blackjack.game();

        String output = outputStreamCaptor.toString();

        assertTrue(output.contains("Please answer y/n"));
        assertTrue(output.contains("Good game!"));
    }

    @Test
    void testGameOneRoundThenExit() {
        String input = "y\n0\nn\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Blackjack blackjack = new Blackjack();
        blackjack.game();

        String output = outputStreamCaptor.toString();

        assertTrue(output.contains("New round?"));
        assertTrue(output.contains("Your hand:"));
        assertTrue(output.contains("Dealer's hand:"));
        assertTrue(output.contains("Good game!"));
    }

    @Test
    void testPlayerBust() {
        String input = "y\n1\n0\nn\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Deck testDeck = new Deck(true);
        testDeck.addCardForTest(new Card(Suit.HEART, Value.TEN));
        testDeck.addCardForTest(new Card(Suit.HEART, Value.EIGHT));
        testDeck.addCardForTest(new Card(Suit.CLUB, Value.QUEEN));
        testDeck.addCardForTest(new Card(Suit.DIAMOND, Value.TEN));
        testDeck.addCardForTest(new Card(Suit.HEART, Value.KING));

        Blackjack blackjack = new Blackjack(testDeck);
        blackjack.game();

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("You lost! Bust!"));
    }


    @Test
    void testPlayerWin() {
        String input = "y\n0\nn\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Deck testDeck = new Deck(true);
        testDeck.addCardForTest(new Card(Suit.SPADE, Value.KING));
        testDeck.addCardForTest(new Card(Suit.HEART, Value.SEVEN));
        testDeck.addCardForTest(new Card(Suit.HEART, Value.TEN));
        testDeck.addCardForTest(new Card(Suit.DIAMOND, Value.TEN));

        Blackjack blackjack = new Blackjack(testDeck);
        blackjack.game();

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("You won!"));
    }

    @Test
    void testDealerWin() {
        String input = "y\n0\nn\n"; // Start round, stand, end game
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Deck testDeck = new Deck(true);
        testDeck.addCardForTest(new Card(Suit.HEART, Value.TEN));
        testDeck.addCardForTest(new Card(Suit.CLUB, Value.ACE));
        testDeck.addCardForTest(new Card(Suit.DIAMOND, Value.TWO));
        testDeck.addCardForTest(new Card(Suit.HEART, Value.KING));

        Blackjack blackjack = new Blackjack(testDeck);
        blackjack.game();

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("You lost!"));
    }

    @Test
    void testPlayerBlackjack() {
        String input = "y\n0\nn\n"; // Start round, end game
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Deck testDeck = new Deck(true);
        testDeck.addCardForTest(new Card(Suit.HEART, Value.ACE));
        testDeck.addCardForTest(new Card(Suit.HEART, Value.KING));
        testDeck.addCardForTest(new Card(Suit.CLUB, Value.TEN));
        testDeck.addCardForTest(new Card(Suit.DIAMOND, Value.NINE));

        Blackjack blackjack = new Blackjack(testDeck);
        blackjack.game();

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("You won!"));
    }
}
