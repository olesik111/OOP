package ru.nsu.kataeva;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValueTest {

    @Test
    public void testValueValues() {
        Value[] values = Value.values();
        assertEquals(13, values.length);
    }

    @Test
    public void testValueToString() {
        assertEquals("Tuz", Value.ACE.toString());
        assertEquals("Dva", Value.TWO.toString());
        assertEquals("Korol", Value.KING.toString());
    }

    @Test
    public void testValueVal() {
        assertEquals(11, Value.ACE.val);
        assertEquals(2, Value.TWO.val);
        assertEquals(10, Value.KING.val);
        assertEquals(10, Value.QUEEN.val);
        assertEquals(10, Value.JACK.val);
    }

    @Test
    public void testValueName() {
        assertEquals("Tuz", Value.ACE.name);
        assertEquals("Dva", Value.TWO.name);
        assertEquals("Korol", Value.KING.name);
    }
}