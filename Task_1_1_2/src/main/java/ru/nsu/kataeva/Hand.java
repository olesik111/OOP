package ru.nsu.kataeva;

import java.util.ArrayList;

/**
 * Represents a hand of playing cards.
 */
public class Hand {
    private final ArrayList<Card> cards;

    /**
     * Creates an empty hand.
     */
    public Hand() {
        this.cards = new ArrayList<>();
    }

    /**
     * Takes a card from another deck and adds to this hand.
     *
     * @param from the deck to take card from
     */
    public void takeForRound(Deck from) {
        this.cards.add(from.takeCardAndRemove());
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
     * Calculates the total value of cards in hand, handling aces.
     *
     * @return sum
     */
    public int cardsInHand() {
        int sum = 0;
        int ace = 0;

        for (Card card : cards) {
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
     * @return bj or not.
     */
    public boolean checkForWin() {
        return this.cardsInHand() == 21;
    }

    /**
     * Checks for blackjack (exactly 21 with 2 cards).
     *
     * @return true if blackjacked.
     */
    public boolean hasBlackjack() {
        return this.cardsInHand() == 21 && this.cards.size() == 2;
    }

    /**
     * Checks if hand is bust (over 21).
     *
     * @return true if bust.
     */
    public boolean isBust() {
        return this.cardsInHand() > 21;
    }

    /**
     * Adds a specific card for testing purposes.
     *
     * @param card specific card.
     */
    public void addCardForTest(Card card) {
        this.cards.add(card);
    }

    public int getCardCount() {
        return this.cards.size();
    }

    @Override
    public String toString() {
        StringBuilder hand = new StringBuilder();

        for (int i = 0; i < cards.size(); i++) {
            hand.append(cards.get(i).toString());
            if (i < cards.size() - 1) {
                hand.append(", ");
            }
        }

        return hand.toString();
    }
}