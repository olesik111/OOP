package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

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

    @Test
    public void testBlackjackConstructor() {
        String input = "n\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertDoesNotThrow(() -> {
            Blackjack game = new Blackjack();
            assertNotNull(game);
        });
    }


    @Test
    public void testPlayerDecisionHit() {
        Decision decision = new Decision();
        Deck playerHand = new Deck();
        Deck gameDeck = new Deck();
        gameDeck.createDeck();
        String input = "1\n0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertDoesNotThrow(() -> decision.playerDecision(playerHand, gameDeck));
    }

    @Test
    public void testPlayerDecisionStand() {
        Decision decision = new Decision();
        Deck playerHand = new Deck();
        Deck gameDeck = new Deck();
        String input = "0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertDoesNotThrow(() -> decision.playerDecision(playerHand, gameDeck));
    }

    @Test
    public void testLess17DecisionPlayerWins() {
        Decision decision = new Decision();
        Deck playerHand = new Deck();
        Deck dealerHand = new Deck();
        playerHand.addCardForTest(new Card(Suit.HEART, Value.KING));
        dealerHand.addCardForTest(new Card(Suit.SPADE, Value.QUEEN));
        assertDoesNotThrow(() -> decision.less17Decision(playerHand, dealerHand, 0, 0));
    }

    @Test
    public void testDeckShuffle() {
        Deck deck = new Deck();
        deck.createDeck();
        String originalOrder = deck.toString();
        deck.shuffle();
        String shuffledOrder = deck.toString();
        assertNotEquals(originalOrder, shuffledOrder);
    }

    @Test
    public void testDeckToString() {
        Deck deck = new Deck();
        deck.addCardForTest(new Card(Suit.HEART, Value.ACE));
        deck.addCardForTest(new Card(Suit.SPADE, Value.KING));
        String result = deck.toString();
        assertTrue(result.contains("Tuz Chervi"));
        assertTrue(result.contains("Korol Piki"));
    }

    @Test
    public void testSuitToString() {
        assertEquals("Chervi", Suit.HEART.toString());
        assertEquals("Trefy", Suit.CLUB.toString());
    }

    @Test
    public void testValueToString() {
        assertEquals("Tuz", Value.ACE.toString());
        assertEquals("Korol", Value.KING.toString());
    }

    @Test
    public void testAceValueReduction() {
        Deck hand = new Deck();
        hand.addCardForTest(new Card(Suit.HEART, Value.ACE));
        hand.addCardForTest(new Card(Suit.SPADE, Value.ACE));
        hand.addCardForTest(new Card(Suit.CLUB, Value.KING));
        assertEquals(12, hand.cardsInHand(hand));
    }

    @Test
    public void testEmptyDeckException() {
        Deck emptyDeck = new Deck();
        assertThrows(IllegalStateException.class, emptyDeck::takeCardAndRemove);
    }
}