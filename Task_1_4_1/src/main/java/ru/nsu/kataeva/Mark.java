package ru.nsu.kataeva;

/**
 * Enum of possible marks.
 */
public enum Mark {
    FAIL(2),
    SATISFACTORY(3),
    GOOD(4),
    EXCELLENT(5);

    private final int value;

    Mark(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    /**
     * Convert integer to Mark.
     *
     * @param value integer value
     */
    public static Mark fromInt(int value) {
        for (Mark mark : values()) {
            if (mark.value == value) {
                return mark;
            }
        }
        throw new IllegalArgumentException("Invalid mark value: " + value + ". Allowed values: 2-5");
    }
}
