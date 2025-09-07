// DeckTest.java
package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeckTest {
    private Deck deck;
    private Deck emptyDeck;
    private Hand hand;

    @BeforeEach
    void setUp() {
        deck = new Deck(true);
        emptyDeck = new Deck(true);
        hand = new Hand();
    }

    @Test
    void testConstructorWithFullDeck() {
        Deck fullDeck = new Deck();
        assertNotNull(fullDeck);
        assertEquals(52, fullDeck.getRemainingCards());
    }

    @Test
    void testConstructorWithEmptyDeck() {
        assertNotNull(deck);
        assertEquals(0, deck.getRemainingCards());
    }

    @Test
    void testCreateDeck() {
        deck.createDeck();
        assertEquals(52, deck.getRemainingCards());
    }

    @Test
    void testTakeCardAndRemove() {
        deck.createDeck();
        int initialSize = deck.getRemainingCards();

        Card card = deck.takeCardAndRemove();

        assertNotNull(card);
        assertEquals(initialSize - 1, deck.getRemainingCards());
    }

    @Test
    void testTakeCardAndRemoveFromEmptyDeck() {
        assertThrows(IllegalStateException.class, () -> emptyDeck.takeCardAndRemove());
    }

    @Test
    void testGetCard() {
        deck.createDeck();

        Card firstCard = deck.getCard(0);
        Card lastCard = deck.getCard(51);

        assertNotNull(firstCard);
        assertNotNull(lastCard);
        assertNotEquals(firstCard, lastCard);
    }

    @Test
    void testTakeForRound() {
        Deck sourceDeck = new Deck(true);
        sourceDeck.createDeck();
        int initialSourceSize = sourceDeck.getRemainingCards();

        hand.takeForRound(sourceDeck);

        assertEquals(1, hand.getCardCount());
        assertEquals(initialSourceSize - 1, sourceDeck.getRemainingCards());
    }

    @Test
    void testShuffle() {
        deck.createDeck();

        deck.shuffle();
        Card firstCardAfterShuffle = deck.getCard(0);

        assertEquals(52, deck.getRemainingCards());
        assertNotNull(firstCardAfterShuffle);
    }

    @Test
    void testCardsInHandBasicValues() {
        hand.addCardForTest(new Card(Suit.HEART, Value.TWO));
        hand.addCardForTest(new Card(Suit.CLUB, Value.THREE));

        assertEquals(5, hand.cardsInHand());
    }

    @Test
    void testCardsInHandWithAceSoft() {
        hand.addCardForTest(new Card(Suit.HEART, Value.ACE));
        hand.addCardForTest(new Card(Suit.CLUB, Value.NINE));

        assertEquals(20, hand.cardsInHand());
    }

    @Test
    void testCardsInHandWithAceHard() {
        hand.addCardForTest(new Card(Suit.HEART, Value.ACE));
        hand.addCardForTest(new Card(Suit.CLUB, Value.TEN));
        hand.addCardForTest(new Card(Suit.DIAMOND, Value.FIVE));

        assertEquals(16, hand.cardsInHand());
    }

    @Test
    void testCheckForWinTrue() {
        hand.addCardForTest(new Card(Suit.HEART, Value.ACE));
        hand.addCardForTest(new Card(Suit.CLUB, Value.KING));

        assertTrue(hand.checkForWin());
    }

    @Test
    void testCheckForWinFalse() {
        hand.addCardForTest(new Card(Suit.HEART, Value.ACE));
        hand.addCardForTest(new Card(Suit.CLUB, Value.NINE));

        assertFalse(hand.checkForWin());
    }

    @Test
    void testToStringEmptyHand() {
        assertEquals("", hand.toString());
    }

    @Test
    void testToStringWithCards() {
        hand.addCardForTest(new Card(Suit.HEART, Value.ACE));
        hand.addCardForTest(new Card(Suit.CLUB, Value.TEN));

        String result = hand.toString();
        assertTrue(result.contains("Tuz Chervi"));
        assertTrue(result.contains("Desyat Trefy"));
        assertTrue(result.contains(", "));
    }

    @Test
    void testAddCardForTest() {
        Card testCard = new Card(Suit.DIAMOND, Value.SEVEN);

        hand.addCardForTest(testCard);

        assertEquals(1, hand.getCardCount());
        assertTrue(hand.toString().contains("Sem Bubny"));
    }
}