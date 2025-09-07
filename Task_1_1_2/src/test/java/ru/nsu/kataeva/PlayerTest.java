package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Player tests.
 */
public class PlayerTest {
    private Hand playerHand;
    private Deck gameDeck;

    @BeforeEach
    void setUp() {
        playerHand = new Hand();
        gameDeck = new Deck(true);
        gameDeck.createDeck();
    }

    @Test
    void testInitialDeal() {
        assertTrue(playerHand.toString().isEmpty());
        assertEquals(52, gameDeck.toString().split(",").length);

        for (int i = 0; i < 2; i++) {
            playerHand.takeForRound(gameDeck);
        }

        assertEquals(2, playerHand.toString().split(", ").length);
        assertEquals(50, gameDeck.toString().split(",").length);
    }

    @Test
    void testPlayerHitBlackjack() {
        // Setup hand with 10 points
        playerHand.addCardForTest(new Card(Suit.CLUB, Value.FIVE));
        playerHand.addCardForTest(new Card(Suit.DIAMOND, Value.FIVE));
        assertEquals(10, playerHand.cardsInHand());

        gameDeck = new Deck(true);
        gameDeck.addCardForTest(new Card(Suit.HEART, Value.ACE));

        playerHand.takeForRound(gameDeck);

        assertEquals(3, playerHand.toString().split(", ").length);
        assertEquals(21, playerHand.cardsInHand());
        assertTrue(playerHand.checkForWin());
    }

    @Test
    void testPlayerBust() {

        playerHand.addCardForTest(new Card(Suit.CLUB, Value.KING));
        playerHand.addCardForTest(new Card(Suit.DIAMOND, Value.QUEEN));
        assertEquals(20, playerHand.cardsInHand());

        gameDeck = new Deck(true);
        gameDeck.addCardForTest(new Card(Suit.HEART, Value.TWO));

        playerHand.takeForRound(gameDeck);

        assertEquals(3, playerHand.toString().split(", ").length);
        assertEquals(22, playerHand.cardsInHand());
        assertTrue(playerHand.cardsInHand() > 21);
        assertTrue(gameDeck.toString().isEmpty());
    }
}
