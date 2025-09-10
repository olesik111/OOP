package ru.nsu.kataeva;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a deck of playing cards and handles deck operations.
 */
public class Deck {
    private final ArrayList<Card> cards;

    /**
     * Creates a standard 52-card deck for game and shuffles it.
     */
    public Deck() {
        this.cards = new ArrayList<>();
        createDeck(cards);
        shuffle();
    }

    /**
     * Creates an empty deck for testing.
     */
    public Deck(boolean ignoredForTesting) {
        this.cards = new ArrayList<>();
        // Empty deck for testing
    }

    /**
     * Creates a standard 52-card deck.
     */
    public static void createDeck(ArrayList<Card> cards) {
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                cards.add(new Card(suit, value));
            }
        }
    }

    /**
     * Draws and removes the top card from the deck.
     */
    public Card takeCardAndRemove() {
        if (this.cards.isEmpty()) {
            throw new IllegalStateException("The deck is empty. Restart, please");
        }
        Card card = this.cards.get(0);
        this.cards.remove(0);
        return card;
    }

    /**
     * Gets specific card.
     *
     * @param i position.
     * @return cards at position.
     */
    public Card getCard(int i) {
        return this.cards.get(i);
    }

    /**
     * Shuffles the deck using random selection.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Adds a specific card for testing purposes.
     *
     * @param card specific card.
     */
    public void addCardForTest(Card card) {
        this.cards.add(card);
    }

    /**
     * Adds all cards from another collection to this deck.
     *
     * @param cards the collection of cards to add
     */
    public void addAll(ArrayList<Card> cards) {
        this.cards.addAll(cards);
    }

    public int getRemainingCards() {
        return this.cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }

    @Override
    public String toString() {
        StringBuilder deck = new StringBuilder();

        for (int i = 0; i < cards.size(); i++) {
            deck.append(cards.get(i).toString());
            if (i < cards.size() - 1) {
                deck.append(",");
            }
        }

        return deck.toString();
    }
}