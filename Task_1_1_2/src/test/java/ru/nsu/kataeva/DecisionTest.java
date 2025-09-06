package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DecisionTest {
    private Decision decision;
    private Deck playerHand;
    private Deck dealerHand;
    private Deck gameDeck;

    @BeforeEach
    void setUp() {
        decision = new Decision();
        playerHand = new Deck();
        dealerHand = new Deck();
        gameDeck = new Deck();
    }

    @Test
    void testPlayerDecisionHit() {
        playerHand.addCardForTest(new Card(Suit.HEART, Value.TEN));
        playerHand.addCardForTest(new Card(Suit.CLUB, Value.SIX));
        assertEquals(16, playerHand.cardsInHand(playerHand));

        gameDeck.addCardForTest(new Card(Suit.DIAMOND, Value.FIVE));

        java.io.ByteArrayInputStream in = new java.io.ByteArrayInputStream("1\n0\n".getBytes());
        System.setIn(in);

        decision.playerDecision(playerHand, gameDeck);

        assertEquals(3, playerHand.toString().split(", ").length);
        assertEquals(21, playerHand.cardsInHand(playerHand));
        assertTrue(gameDeck.toString().isEmpty());
    }

    @Test
    void testPlayerDecisionStand() {
        // Setup initial hand
        playerHand.addCardForTest(new Card(Suit.HEART, Value.TEN));
        playerHand.addCardForTest(new Card(Suit.CLUB, Value.SEVEN));
        int initialPoints = playerHand.cardsInHand(playerHand);


        java.io.ByteArrayInputStream in = new java.io.ByteArrayInputStream("0\n".getBytes());
        System.setIn(in);

        decision.playerDecision(playerHand, gameDeck);

        assertEquals(2, playerHand.toString().split(", ").length);
        assertEquals(initialPoints, playerHand.cardsInHand(playerHand));
    }

    @Test
    void testLess17DecisionDealerWins() {
        int winDealer = 0;
        int winPlayer = 0;

        // Dealer has higher score (18 vs 17)
        dealerHand.addCardForTest(new Card(Suit.HEART, Value.TEN));
        dealerHand.addCardForTest(new Card(Suit.CLUB, Value.EIGHT));
        playerHand.addCardForTest(new Card(Suit.DIAMOND, Value.TEN));
        playerHand.addCardForTest(new Card(Suit.SPADE, Value.SEVEN));

        assertEquals(18, dealerHand.cardsInHand(dealerHand));
        assertEquals(17, playerHand.cardsInHand(playerHand));

        decision.less17Decision(playerHand, dealerHand, winDealer, winPlayer);

        assertEquals(18, dealerHand.cardsInHand(dealerHand));
        assertEquals(17, playerHand.cardsInHand(playerHand));
    }

    @Test
    void testLess17DecisionPlayerWins() {
        int winDealer = 0;
        int winPlayer = 0;

        playerHand.addCardForTest(new Card(Suit.HEART, Value.TEN));
        playerHand.addCardForTest(new Card(Suit.CLUB, Value.NINE));
        dealerHand.addCardForTest(new Card(Suit.DIAMOND, Value.TEN));
        dealerHand.addCardForTest(new Card(Suit.SPADE, Value.SEVEN));

        assertEquals(19, playerHand.cardsInHand(playerHand));
        assertEquals(17, dealerHand.cardsInHand(dealerHand));

        decision.less17Decision(playerHand, dealerHand, winDealer, winPlayer);

        assertEquals(19, playerHand.cardsInHand(playerHand));
        assertEquals(17, dealerHand.cardsInHand(dealerHand));
    }

    @Test
    void testLess17DecisionDraw() {
        int winDealer = 0;
        int winPlayer = 0;

        playerHand.addCardForTest(new Card(Suit.HEART, Value.TEN));
        playerHand.addCardForTest(new Card(Suit.CLUB, Value.EIGHT));
        dealerHand.addCardForTest(new Card(Suit.DIAMOND, Value.NINE));
        dealerHand.addCardForTest(new Card(Suit.SPADE, Value.NINE));

        assertEquals(18, playerHand.cardsInHand(playerHand));
        assertEquals(18, dealerHand.cardsInHand(dealerHand));

        decision.less17Decision(playerHand, dealerHand, winDealer, winPlayer);

        assertEquals(18, playerHand.cardsInHand(playerHand));
        assertEquals(18, dealerHand.cardsInHand(dealerHand));
    }

}