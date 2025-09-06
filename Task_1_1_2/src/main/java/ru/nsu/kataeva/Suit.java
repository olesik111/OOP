package ru.nsu.kataeva;

/**
 * Represents the four suits of playing cards with Russian names.
 */
public enum Suit {
    HEART("Chervi"),
    CLUB("Trefy"),
    DIAMOND("Bubny"),
    SPADE("Piki");

    final String russianName;

    Suit(String russianName) {
        this.russianName = russianName;
    }

    @Override
    public String toString() {
        return russianName;
    }
}
