package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Hand tests.
 */
public class HandTest {

    @Test
    void testHasBlackjack() {
        Hand hand = new Hand();
        hand.addCardForTest(new Card(Suit.HEART, Value.ACE));
        hand.addCardForTest(new Card(Suit.CLUB, Value.KING));

        assertTrue(hand.hasBlackjack());
    }

    @Test
    void testHasBlackjackFalseWithThreeCards() {
        Hand hand = new Hand();
        hand.addCardForTest(new Card(Suit.HEART, Value.ACE));
        hand.addCardForTest(new Card(Suit.CLUB, Value.FIVE));
        hand.addCardForTest(new Card(Suit.DIAMOND, Value.FIVE));

        assertFalse(hand.hasBlackjack());
    }

    @Test
    void testIsBust() {
        Hand hand = new Hand();
        hand.addCardForTest(new Card(Suit.HEART, Value.KING));
        hand.addCardForTest(new Card(Suit.CLUB, Value.QUEEN));
        hand.addCardForTest(new Card(Suit.DIAMOND, Value.TWO));

        assertTrue(hand.isBust());
    }

    @Test
    void testIsNotBust() {
        Hand hand = new Hand();
        hand.addCardForTest(new Card(Suit.HEART, Value.KING));
        hand.addCardForTest(new Card(Suit.CLUB, Value.FIVE));

        assertFalse(hand.isBust());
    }
}