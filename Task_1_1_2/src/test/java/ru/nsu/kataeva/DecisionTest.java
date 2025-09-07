package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Decision Test.
 */
public class DecisionTest {
    private Decision decision;
    private Hand playerHand;
    private Hand dealerHand;
    private Deck gameDeck;
    private final InputStream standardIn = System.in;

    @BeforeEach
    void setUp() {
        decision = new Decision();
        playerHand = new Hand();
        dealerHand = new Hand();
        gameDeck = new Deck(true);
    }

    @AfterEach
    void tearDown() {
        System.setIn(standardIn);
    }

    @Test
    void testPlayerDecisionHit() {
        playerHand.addCardForTest(new Card(Suit.HEART, Value.TEN));
        playerHand.addCardForTest(new Card(Suit.CLUB, Value.SIX));
        assertEquals(16, playerHand.cardsInHand());

        gameDeck.addCardForTest(new Card(Suit.DIAMOND, Value.FIVE));

        ByteArrayInputStream in = new ByteArrayInputStream("1\n0\n".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(in);

        decision.playerDecision(playerHand, gameDeck, scanner);

        assertEquals(3, playerHand.toString().split(", ").length);
        assertEquals(21, playerHand.cardsInHand());
        assertTrue(gameDeck.toString().isEmpty());
    }

    @Test
    void testPlayerDecisionStand() {
        playerHand.addCardForTest(new Card(Suit.HEART, Value.TEN));
        playerHand.addCardForTest(new Card(Suit.CLUB, Value.SEVEN));
        final int initialPoints = playerHand.cardsInHand();


        ByteArrayInputStream in = new ByteArrayInputStream("0\n".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(in);

        decision.playerDecision(playerHand, gameDeck, scanner);

        assertEquals(2, playerHand.toString().split(", ").length);
        assertEquals(initialPoints, playerHand.cardsInHand());
    }

    @Test
    void testLess17DecisionDealerWins() {
        final int winDealer = 0;
        final int winPlayer = 0;

        dealerHand.addCardForTest(new Card(Suit.HEART, Value.TEN));
        dealerHand.addCardForTest(new Card(Suit.CLUB, Value.EIGHT));
        playerHand.addCardForTest(new Card(Suit.DIAMOND, Value.TEN));
        playerHand.addCardForTest(new Card(Suit.SPADE, Value.SEVEN));

        assertEquals(18, dealerHand.cardsInHand());
        assertEquals(17, playerHand.cardsInHand());

        decision.less17Decision(playerHand, dealerHand, winDealer, winPlayer);

        assertEquals(18, dealerHand.cardsInHand());
        assertEquals(17, playerHand.cardsInHand());
    }

    @Test
    void testLess17DecisionPlayerWins() {
        final int winDealer = 0;
        final int winPlayer = 0;

        playerHand.addCardForTest(new Card(Suit.HEART, Value.TEN));
        playerHand.addCardForTest(new Card(Suit.CLUB, Value.NINE));
        dealerHand.addCardForTest(new Card(Suit.DIAMOND, Value.TEN));
        dealerHand.addCardForTest(new Card(Suit.SPADE, Value.SEVEN));

        assertEquals(19, playerHand.cardsInHand());
        assertEquals(17, dealerHand.cardsInHand());

        decision.less17Decision(playerHand, dealerHand, winDealer, winPlayer);

        assertEquals(19, playerHand.cardsInHand());
        assertEquals(17, dealerHand.cardsInHand());
    }

    @Test
    void testLess17DecisionDraw() {
        final int winDealer = 0;
        final int winPlayer = 0;

        playerHand.addCardForTest(new Card(Suit.HEART, Value.TEN));
        playerHand.addCardForTest(new Card(Suit.CLUB, Value.EIGHT));
        dealerHand.addCardForTest(new Card(Suit.DIAMOND, Value.NINE));
        dealerHand.addCardForTest(new Card(Suit.SPADE, Value.NINE));

        assertEquals(18, playerHand.cardsInHand());
        assertEquals(18, dealerHand.cardsInHand());

        decision.less17Decision(playerHand, dealerHand, winDealer, winPlayer);

        assertEquals(18, playerHand.cardsInHand());
        assertEquals(18, dealerHand.cardsInHand());
    }

}