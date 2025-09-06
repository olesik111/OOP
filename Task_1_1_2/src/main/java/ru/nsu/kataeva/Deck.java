package ru.nsu.kataeva;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a deck of playing cards and handles deck operations.
 */
public class Deck {
    private ArrayList<Card> cards;

    /**
     * Creates an empty deck.
     */
    public Deck() {
        this.cards = new ArrayList<>();
    }

    /**
     * Creates a standard 52-card deck.
     */
    public void createDeck() {
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                this.cards.add(new Card(suit, value));
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
     * @return cards at i position.
     */
    public Card getCard(int i) {
        return this.cards.get(i);
    }

    /**
     * Takes a card from another deck and adds to this one.
     *
     * @param from the deck to take card
     */
    public void takeForRound(Deck from) {
        this.cards.add(from.takeCardAndRemove());
    }

    /**
     * Shuffles the deck using random selection.
     */
    public void shuffle() {
        ArrayList<Card> newDeck = new ArrayList<>();
        Random random = new Random();
        int randInd;
        int size = this.cards.size();

        for (int i = 0; i < size; i++) {
            randInd = random.nextInt(this.cards.size());
            newDeck.add(this.cards.get(randInd));
            this.cards.remove(randInd);
        }

        this.cards = newDeck;
    }

    /**
     * Calculates the total value of cards in hand, handling aces.
     *
     * @param hand counts value of this hand
     * @return sum
     */
    public int cardsInHand(Deck hand) {
        int sum = 0;
        int ace = 0;

        for (Card card : hand.cards) {
            sum = sum + card.getValue();
            if (card.getValue() == 11) {
                ace++;
            }
        }

        if (sum > 21 && ace > 0) {
            while (ace > 0 && sum > 21) {
                ace--;
                sum -= 10;
            }
        }

        return sum;
    }

    /**
     * Checks for blackjack.
     *
     * @param hand hand to check.
     * @return bj or not.
     */
    public boolean checkForWin(Deck hand) {
        return this.cardsInHand(hand) == 21;
    }

    @Override
    public String toString() {
        StringBuilder deck = new StringBuilder();

        for (int i = 0; i < cards.size(); i++) {
            deck.append(cards.get(i).toString());
            if (i < cards.size() - 1) {
                deck.append(", ");
            }
        }

        return deck.toString();
    }

    /**
     * Adds a specific card for testing purposes.
     *
     * @param card specific card.
     */
    public void addCardForTest(Card card) {
        this.cards.add(card);
    }

}
