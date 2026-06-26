package ru.nsu.kataeva;

import java.util.Objects;

/**
 * Card constructor.
 *
 * @param suit  suit of card.
 * @param value value of card.
 */
public record Card(Suit suit, Value value) {

    @Override
    public String toString() {
        return this.value.toString() + " " + this.suit.toString();
    }

    /**
     * Returns the value of the card.
     */
    public int getValue() {
        return this.value.val;
    }

    /**
     * function to compare.
     *
     * @param o   the reference object with which to compare.
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return suit == card.suit && value == card.value;
    }

    /**
     * function for hash code.
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(suit, value);
    }
}
