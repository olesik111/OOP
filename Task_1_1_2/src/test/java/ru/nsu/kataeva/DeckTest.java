package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Deck class.
 */
public class DeckTest {

    private Deck deck;

    @BeforeEach
    public void setUp() {
        deck = new Deck();
    }

    @Test
    public void testCreateDeck() {
        deck.createDeck();
        assertEquals(52, deck.toString().split(", ").length);
    }

    @Test
    public void testTakeCardAndRemove() {
        deck.createDeck();
        int initialSize = deck.toString().split(", ").length;

        Card card = deck.takeCardAndRemove();
        assertNotNull(card);

        int newSize = deck.toString().split(", ").length;
        assertEquals(initialSize - 1, newSize);
    }

    @Test
    public void testTakeCardAndRemoveFromEmptyDeck() {
        Card card = deck.takeCardAndRemove();
        assertNull(card);
    }

    @Test
    public void testGetCard() {
        deck.createDeck();
        Card card = deck.getCard(0);
        assertNotNull(card);
    }

    @Test
    public void testTakeForRound() {
        Deck sourceDeck = new Deck();
        sourceDeck.createDeck();

        Deck targetDeck = new Deck();
        targetDeck.takeForRound(sourceDeck);

        assertEquals(1, targetDeck.toString().split(", ").length);
        assertEquals(51, sourceDeck.toString().split(", ").length);
    }

    @Test
    public void testShuffle() {
        deck.createDeck();
        String originalOrder = deck.toString();
        deck.shuffle();
        String shuffledOrder = deck.toString();

        assertNotEquals("", shuffledOrder);
        assertEquals(52, shuffledOrder.split(", ").length);

        for (String cardStr : originalOrder.split(", ")) {
            assertTrue(shuffledOrder.contains(cardStr));
        }
    }

    @Test
    public void testCardsInHand() {
        Deck hand = new Deck();

        assertEquals(0, hand.cardsInHand(hand));

        hand.addCardForTest(new Card(Suit.HEART, Value.QUEEN));
        hand.addCardForTest(new Card(Suit.HEART, Value.KING));

        assertEquals(20, hand.cardsInHand(hand));
    }

    @Test
    public void testCardsInHandWithAce() {
        Deck hand = new Deck();

        hand.addCardForTest(new Card(Suit.HEART, Value.ACE));
        hand.addCardForTest(new Card(Suit.HEART, Value.KING));
        assertEquals(21, hand.cardsInHand(hand));

        Deck hand2 = new Deck();
        hand2.addCardForTest(new Card(Suit.HEART, Value.ACE));
        hand2.addCardForTest(new Card(Suit.HEART, Value.SEVEN));
        hand2.addCardForTest(new Card(Suit.HEART, Value.FIVE));
        assertEquals(13, hand2.cardsInHand(hand2));
    }

    @Test
    public void testCheckForWinWithBlackjack() {
        Deck blackjackHand = new Deck();
        blackjackHand.addCardForTest(new Card(Suit.HEART, Value.ACE));
        blackjackHand.addCardForTest(new Card(Suit.HEART, Value.KING));
        assertTrue(blackjackHand.checkForWin(blackjackHand));
    }

    @Test
    public void testCheckForWinWithoutBlackjack() {
        Deck nonBlackjackHand = new Deck();
        nonBlackjackHand.addCardForTest(new Card(Suit.HEART, Value.KING));
        nonBlackjackHand.addCardForTest(new Card(Suit.HEART, Value.QUEEN));
        assertFalse(nonBlackjackHand.checkForWin(nonBlackjackHand));
    }

    @Test
    public void testCheckForWinWith21ButNotBlackjack() {
        Deck hand = new Deck();
        hand.addCardForTest(new Card(Suit.HEART, Value.KING));
        hand.addCardForTest(new Card(Suit.HEART, Value.QUEEN));
        hand.addCardForTest(new Card(Suit.HEART, Value.ACE));
        assertTrue(hand.checkForWin(hand)); // 10 + 10 + 1 = 21
    }

    @Test
    public void testToString() {
        Deck testDeck = new Deck();
        testDeck.addCardForTest(new Card(Suit.HEART, Value.ACE));
        testDeck.addCardForTest(new Card(Suit.HEART, Value.KING));

        String result = testDeck.toString();
        assertEquals("Tuz Chervi, Korol Chervi", result);
    }

    @Test
    public void testToStringEmptyDeck() {
        Deck emptyDeck = new Deck();
        assertEquals("", emptyDeck.toString());
    }

    @Test
    public void testToStringSingleCard() {
        Deck singleCardDeck = new Deck();
        singleCardDeck.addCardForTest(new Card(Suit.SPADE, Value.QUEEN));

        String result = singleCardDeck.toString();
        assertEquals("Dama Piki", result);
    }

    @Test
    public void testToStringMultipleCards() {
        Deck multiCardDeck = new Deck();
        multiCardDeck.addCardForTest(new Card(Suit.HEART, Value.ACE));
        multiCardDeck.addCardForTest(new Card(Suit.DIAMOND, Value.TWO));
        multiCardDeck.addCardForTest(new Card(Suit.CLUB, Value.THREE));

        String result = multiCardDeck.toString();
        assertEquals("Tuz Chervi, Dva Bubny, Tri Trefy", result);
    }

    @Test
    public void testCardsInHandEmpty() {
        Deck emptyHand = new Deck();
        assertEquals(0, emptyHand.cardsInHand(emptyHand));
    }

    @Test
    public void testCardsInHandSingleCard() {
        Deck singleCardHand = new Deck();
        singleCardHand.addCardForTest(new Card(Suit.HEART, Value.SEVEN));
        assertEquals(7, singleCardHand.cardsInHand(singleCardHand));
    }

    @Test
    public void testCardsInHandBustWithoutAce() {
        Deck bustHand = new Deck();
        bustHand.addCardForTest(new Card(Suit.HEART, Value.KING));
        bustHand.addCardForTest(new Card(Suit.DIAMOND, Value.QUEEN));
        bustHand.addCardForTest(new Card(Suit.CLUB, Value.JACK));
        assertEquals(30, bustHand.cardsInHand(bustHand)); // 10 + 10 + 10 = 30
    }
}