package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Decision class.
 */
public class DecisionTest {

    private Decision decision;
    private Deck playerHand;
    private Deck gameDeck;

    /**
     * New environment.
     */
    @BeforeEach
    public void setUp() {
        decision = new Decision();
        playerHand = new Deck();
        gameDeck = new Deck();
        gameDeck.createDeck();
    }

    @Test
    public void testPlayerDecisionTakeCard() {
        String input = "1\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        int initialSize = playerHand.toString().length();

        decision.playerDecision(playerHand, gameDeck);

        int newSize = playerHand.toString().length();
        assertTrue(newSize > initialSize);

        System.setIn(System.in);
    }

    @Test
    public void testPlayerDecisionPass() {
        String input = "0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        int initialSize = playerHand.toString().length();

        decision.playerDecision(playerHand, gameDeck);

        int newSize = playerHand.toString().length();
        assertEquals(initialSize, newSize);

        System.setIn(System.in);
    }

    @Test
    public void testPlayerDecisionInvalidInput() {
        String input = "error\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        int initialSize = playerHand.toString().length();

        decision.playerDecision(playerHand, gameDeck);

        int newSize = playerHand.toString().length();
        assertEquals(initialSize, newSize);

        System.setIn(System.in);
    }

    @Test
    public void testLess17DecisionPlayerWins() {
        Deck player = new Deck();
        player.addCardForTest(new Card(Suit.HEART, Value.KING));
        player.addCardForTest(new Card(Suit.SPADE, Value.QUEEN));

        Deck dealer = new Deck();
        dealer.addCardForTest(new Card(Suit.HEART, Value.TEN));
        dealer.addCardForTest(new Card(Suit.SPADE, Value.SEVEN));

        assertTrue(player.cardsInHand(player) > dealer.cardsInHand(dealer));
    }

    @Test
    public void testLess17DecisionDealerWins() {
        Deck player = new Deck();
        player.addCardForTest(new Card(Suit.HEART, Value.TEN));
        player.addCardForTest(new Card(Suit.SPADE, Value.SEVEN));

        Deck dealer = new Deck();
        dealer.addCardForTest(new Card(Suit.HEART, Value.KING));
        dealer.addCardForTest(new Card(Suit.SPADE, Value.QUEEN));

        assertTrue(dealer.cardsInHand(dealer) > player.cardsInHand(player));
    }

    @Test
    public void testLess17DecisionDraw() {
        Deck player = new Deck();
        player.addCardForTest(new Card(Suit.HEART, Value.KING));
        player.addCardForTest(new Card(Suit.SPADE, Value.QUEEN));

        Deck dealer = new Deck();
        dealer.addCardForTest(new Card(Suit.DIAMOND, Value.KING));
        dealer.addCardForTest(new Card(Suit.CLUB, Value.QUEEN));

        assertEquals(player.cardsInHand(player), dealer.cardsInHand(dealer));
    }

    @Test
    public void testLess17DecisionWithAces() {
        Deck player = new Deck();
        player.addCardForTest(new Card(Suit.HEART, Value.ACE));
        player.addCardForTest(new Card(Suit.SPADE, Value.ACE)); // 11 + 1 = 12

        Deck dealer = new Deck();
        dealer.addCardForTest(new Card(Suit.DIAMOND, Value.KING));
        dealer.addCardForTest(new Card(Suit.CLUB, Value.SEVEN)); // 10 + 7 = 17

        assertTrue(dealer.cardsInHand(dealer) > player.cardsInHand(player));
    }


}