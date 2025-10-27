package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ConcurrentModificationException;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * Iterator tests.
 */
public class MyIteratorTest {

    @Test
    public void testIterator() {
        HashTable<String, Integer> table = new HashTable<>();
        table.put("a", 1);
        table.put("b", 2);
        table.put("c", 3);

        int count = 0;
        for (Map.Entry<String, Integer> entry : table) {
            assertNotNull(entry.getKey());
            assertNotNull(entry.getValue());
            count++;
        }
        assertEquals(3, count);
    }

    @Test
    public void testIteratorEmpty() {
        HashTable<String, Integer> table = new HashTable<>();

        int count = 0;
        for (Map.Entry<String, Integer> ignored : table) {
            count++;
        }
        assertEquals(0, count);
    }

    @Test
    public void testConcurrentModification() {
        HashTable<String, Integer> table = new HashTable<>();
        table.put("a", 1);
        table.put("b", 2);

        assertThrows(ConcurrentModificationException.class, () -> {
            for (Map.Entry<String, Integer> ignored : table) {
                table.put("c", 3);
            }
        });
    }
}