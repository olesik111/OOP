package ru.nsu.kataeva;

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
}
