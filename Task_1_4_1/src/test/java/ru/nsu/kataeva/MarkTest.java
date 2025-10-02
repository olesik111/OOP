package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests for Mark enum.
 */
public class MarkTest {

    @Test
    public void testFromIntValidValues() {
        assertEquals(Mark.FAIL, Mark.fromInt(2));
        assertEquals(Mark.SATISFACTORY, Mark.fromInt(3));
        assertEquals(Mark.GOOD, Mark.fromInt(4));
        assertEquals(Mark.EXCELLENT, Mark.fromInt(5));
    }

    @Test
    public void testFromIntInvalid() {
        assertThrows(IllegalArgumentException.class, () -> Mark.fromInt(1));
    }
}
