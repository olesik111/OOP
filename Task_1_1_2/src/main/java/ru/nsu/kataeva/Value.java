package ru.nsu.kataeva;

/**
 * Represents card values with Russian names and numerical values.
 */
public enum Value {
    ACE("Tuz", 11),
    TWO("Dva", 2),
    THREE("Tri", 3),
    FOUR("Chetyre", 4),
    FIVE("Pyaterka", 5),
    SIX("Shest", 6),
    SEVEN("Sem", 7),
    EIGHT("Vosem", 8),
    NINE("Devyat", 9),
    TEN("Desyat", 10),
    JACK("Valet", 10),
    QUEEN("Dama", 10),
    KING("Korol", 10);

    final String name;
    final int val;


    Value(String name, int val) {
        this.name = name;
        this.val = val;
    }

    @Override
    public String toString() {
        return name;
    }
}
