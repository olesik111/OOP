package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test class for Blackjack game components.
 */
public class BjTest {

    @Test
    public void testDeckCreation() {
        Deck deck = new Deck();
        deck.createDeck();
        assertEquals(52, deck.toString().split(", ").length);
    }

    @Test
    public void testCardCreation() {
        Card card = new Card(Suit.HEART, Value.ACE);
        assertEquals("Tuz Chervi", card.toString());
        assertEquals(11, card.getValue());
    }

    @Test
    public void testTakeCard() {
        Deck deck = new Deck();
        deck.createDeck();
        Card card = deck.takeCardAndRemove();
        assertNotNull(card);
        assertEquals(51, deck.toString().split(", ").length);
    }

    @Test
    public void testCardsInHand() {
        Deck hand = new Deck();
        hand.addCardForTest(new Card(Suit.HEART, Value.KING));
        hand.addCardForTest(new Card(Suit.SPADE, Value.QUEEN));
        assertEquals(20, hand.cardsInHand(hand));
    }

    @Test
    public void testCheckForWin() {
        Deck hand = new Deck();
        hand.addCardForTest(new Card(Suit.HEART, Value.ACE));
        hand.addCardForTest(new Card(Suit.SPADE, Value.KING));
        assertTrue(hand.checkForWin(hand));
    }
}