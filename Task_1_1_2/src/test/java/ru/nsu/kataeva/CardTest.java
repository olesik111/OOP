package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Test class for Card record.
 */
public class CardTest {

    @Test
    public void testCardCreation() {
        Card card = new Card(Suit.HEART, Value.ACE);
        assertNotNull(card);
        assertEquals(Suit.HEART, card.suit());
        assertEquals(Value.ACE, card.value());
    }

    @Test
    public void testToString() {
        Card card = new Card(Suit.HEART, Value.ACE);
        assertEquals("Tuz Chervi", card.toString());

        Card card2 = new Card(Suit.SPADE, Value.KING);
        assertEquals("Korol Piki", card2.toString());
    }

    @Test
    public void testGetValue() {
        Card ace = new Card(Suit.HEART, Value.ACE);
        assertEquals(11, ace.getValue());

        Card king = new Card(Suit.SPADE, Value.KING);
        assertEquals(10, king.getValue());

        Card seven = new Card(Suit.DIAMOND, Value.SEVEN);
        assertEquals(7, seven.getValue());
    }
}