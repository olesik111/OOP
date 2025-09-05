package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    private Blackjack blackjack;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testBlackjackConstructorInitializesDecks() {
        String input = "n\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        assertDoesNotThrow(() -> {
            Blackjack game = new Blackjack();
            // Проверяем, что колоды созданы
            assertNotNull(game);
        });

        System.setIn(System.in);
    }

    @Test
    void testDealInitialCards() throws Exception {
        Blackjack game = new Blackjack();
        Deck player = new Deck();
        Deck dealer = new Deck();
        Deck gameDeck = new Deck();
        gameDeck.createDeck();

        // Тестируем приватный метод через reflection
        Method dealInitialCards = Blackjack.class.getDeclaredMethod("dealInitialCards", Deck.class, Deck.class);
        dealInitialCards.setAccessible(true);
        dealInitialCards.invoke(game, player, dealer);

        assertEquals(2, player.toString().split(", ").length);
        assertEquals(2, dealer.toString().split(", ").length);
    }

    @Test
    void testShowInitialHands() throws Exception {
        Blackjack game = new Blackjack();
        Deck player = new Deck();
        Deck dealer = new Deck();

        player.addCardForTest(new Card(Suit.HEART, Value.ACE));
        player.addCardForTest(new Card(Suit.SPADE, Value.KING));

        dealer.addCardForTest(new Card(Suit.DIAMOND, Value.QUEEN));
        dealer.addCardForTest(new Card(Suit.CLUB, Value.JACK));

        // Тестируем приватный метод через reflection
        Method showInitialHands = Blackjack.class.getDeclaredMethod("showInitialHands", Deck.class, Deck.class);
        showInitialHands.setAccessible(true);
        showInitialHands.invoke(game, player, dealer);

        String output = outputStream.toString();
        assertTrue(output.contains("Your hand:"));
        assertTrue(output.contains("Dealer's hand:"));
        assertTrue(output.contains("Hidden Card"));
    }

    @Test
    void testGameWithImmediateQuit() {
        String input = "n\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        assertDoesNotThrow(() -> new Blackjack());

        String output = outputStream.toString();
        assertTrue(output.contains("New round?"));

        System.setIn(System.in);
    }
}