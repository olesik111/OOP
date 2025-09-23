package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for Suit enum.
 */
public class SuitTest {

    @Test
    public void testSuitValues() {
        Suit[] suits = Suit.values();
        assertEquals(4, suits.length);
        assertEquals(Suit.HEART, suits[0]);
        assertEquals(Suit.CLUB, suits[1]);
        assertEquals(Suit.DIAMOND, suits[2]);
        assertEquals(Suit.SPADE, suits[3]);
    }

    @Test
    public void testSuitToString() {
        assertEquals("Chervi", Suit.HEART.toString());
        assertEquals("Trefy", Suit.CLUB.toString());
        assertEquals("Bubny", Suit.DIAMOND.toString());
        assertEquals("Piki", Suit.SPADE.toString());
    }

    @Test
    public void testSuitRussianName() {
        assertEquals("Chervi", Suit.HEART.russianName);
        assertEquals("Trefy", Suit.CLUB.russianName);
        assertEquals("Bubny", Suit.DIAMOND.russianName);
        assertEquals("Piki", Suit.SPADE.russianName);
    }
}