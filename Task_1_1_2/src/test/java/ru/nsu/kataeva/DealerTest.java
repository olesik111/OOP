package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Dealer turn test.
 */
public class DealerTest {
    private Deck deck;
    private Hand playerHand;
    private Hand dealerHand;

    @BeforeEach
    void setUp() {
        deck = new Deck(true);
        playerHand = new Hand();
        dealerHand = new Hand();
    }

    @Test
    void testInitialDeal() {
        ArrayList<Card> cards = new ArrayList<>();
        Deck.createDeck(cards);
        deck.addAll(cards);
        assertFalse(deck.toString().isEmpty());
        assertEquals(52, deck.toString().split(",").length);

        for (int i = 0; i < 2; i++) {
            playerHand.takeForRound(deck);
            dealerHand.takeForRound(deck);
        }

        assertEquals(2, playerHand.toString().split(", ").length);
        assertEquals(2, dealerHand.toString().split(", ").length);
        assertEquals(48, deck.toString().split(",").length);
    }

    @Test
    void testPlayerHitUntil21() {
        playerHand.addCardForTest(new Card(Suit.HEART, Value.TEN));
        playerHand.addCardForTest(new Card(Suit.CLUB, Value.SIX));
        assertEquals(16, playerHand.valueInHands());

        deck.addCardForTest(new Card(Suit.DIAMOND, Value.FIVE));

        playerHand.takeForRound(deck);

        assertEquals(3, playerHand.toString().split(", ").length);
        assertEquals(21, playerHand.valueInHands());
        assertTrue(playerHand.checkForWin());
    }

    @Test
    void testPlayerBust() {
        playerHand.addCardForTest(new Card(Suit.HEART, Value.TEN));
        playerHand.addCardForTest(new Card(Suit.CLUB, Value.SIX));
        assertEquals(16, playerHand.valueInHands());

        deck.addCardForTest(new Card(Suit.DIAMOND, Value.TEN));

        playerHand.takeForRound(deck);

        assertEquals(3, playerHand.toString().split(", ").length);
        assertTrue(playerHand.valueInHands() > 21);
        assertEquals(26, playerHand.valueInHands());
    }


}