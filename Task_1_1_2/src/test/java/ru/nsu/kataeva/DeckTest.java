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

    @BeforeEach
    void setUp() {
        deck = new Deck();
        emptyDeck = new Deck();
    }

    @Test
    void testConstructor() {
        assertNotNull(deck);
        assertTrue(deck.toString().isEmpty());
    }

    @Test
    void testCreateDeck() {
        deck.createDeck();

        assertEquals(52, deck.toString().split(", ").length);
        assertTrue(deck.toString().contains("Tuz Chervi"));
        assertTrue(deck.toString().contains("Korol Piki"));
    }

    @Test
    void testTakeCardAndRemove() {
        deck.createDeck();
        int initialSize = deck.toString().split(", ").length;

        Card card = deck.takeCardAndRemove();

        assertNotNull(card);
        assertEquals(initialSize - 1, deck.toString().split(", ").length);
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
        Deck sourceDeck = new Deck();
        sourceDeck.createDeck();
        int initialSourceSize = sourceDeck.toString().split(", ").length;

        emptyDeck.takeForRound(sourceDeck);

        assertEquals(1, emptyDeck.toString().split(", ").length);
        assertEquals(initialSourceSize - 1, sourceDeck.toString().split(", ").length);
    }

    @Test
    void testShuffle() {
        deck.createDeck();
        String originalOrder = deck.toString();

        deck.shuffle();
        String shuffledOrder = deck.toString();

        assertEquals(52, deck.toString().split(", ").length);
        assertNotEquals(originalOrder, shuffledOrder);
    }

    @Test
    void testCardsInHandBasicValues() {
        Deck hand = new Deck();
        hand.addCardForTest(new Card(Suit.HEART, Value.TWO));
        hand.addCardForTest(new Card(Suit.CLUB, Value.THREE));

        assertEquals(5, hand.cardsInHand(hand));
    }

    @Test
    void testCardsInHandWithAceSoft() {
        Deck hand = new Deck();
        hand.addCardForTest(new Card(Suit.HEART, Value.ACE));
        hand.addCardForTest(new Card(Suit.CLUB, Value.NINE));

        assertEquals(20, hand.cardsInHand(hand)); // 11 + 9 = 20
    }

    @Test
    void testCardsInHandWithAceHard() {
        Deck hand = new Deck();
        hand.addCardForTest(new Card(Suit.HEART, Value.ACE));
        hand.addCardForTest(new Card(Suit.CLUB, Value.TEN));
        hand.addCardForTest(new Card(Suit.DIAMOND, Value.FIVE));

        assertEquals(16, hand.cardsInHand(hand));
    }


    @Test
    void testCheckForWinTrue() {
        Deck hand = new Deck();
        hand.addCardForTest(new Card(Suit.HEART, Value.ACE));
        hand.addCardForTest(new Card(Suit.CLUB, Value.KING));

        assertTrue(hand.checkForWin(hand));
    }

    @Test
    void testCheckForWinFalse() {
        Deck hand = new Deck();
        hand.addCardForTest(new Card(Suit.HEART, Value.ACE));
        hand.addCardForTest(new Card(Suit.CLUB, Value.NINE));

        assertFalse(hand.checkForWin(hand));
    }

    @Test
    void testToStringEmptyDeck() {
        assertEquals("", emptyDeck.toString());
    }

    @Test
    void testToStringWithCards() {
        deck.addCardForTest(new Card(Suit.HEART, Value.ACE));
        deck.addCardForTest(new Card(Suit.CLUB, Value.TEN));

        String result = deck.toString();
        assertTrue(result.contains("Tuz Chervi"));
        assertTrue(result.contains("Desyat Trefy"));
        assertTrue(result.contains(", "));
    }

    @Test
    void testAddCardForTest() {
        Card testCard = new Card(Suit.DIAMOND, Value.SEVEN);

        emptyDeck.addCardForTest(testCard);

        assertEquals(1, emptyDeck.toString().split(", ").length);
        assertTrue(emptyDeck.toString().contains("Sem Bubny"));
    }

}