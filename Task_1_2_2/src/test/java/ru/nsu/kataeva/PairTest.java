package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Pair test.
 */
public class PairTest {

    @Test
    public void testPair() {
        Pair<String, Integer> pair = new Pair<>("key", 123);

        assertEquals("key", pair.getKey());
        assertEquals(123, pair.getValue());

        Integer oldValue = pair.setValue(456);
        assertEquals(123, oldValue);
        assertEquals(456, pair.getValue());
    }

    @Test
    public void testKeyEquals() {
        Pair<String, Integer> pair = new Pair<>("test", 1);

        assertTrue(pair.keyEquals("test"));
        assertFalse(pair.keyEquals("other"));
        assertFalse(pair.keyEquals(null));

        Pair<String, Integer> nullPair = new Pair<>(null, 1);
        assertTrue(nullPair.keyEquals(null));
    }
}