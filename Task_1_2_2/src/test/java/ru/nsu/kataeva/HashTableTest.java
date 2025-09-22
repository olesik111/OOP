package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Hash table tests.
 */
public class HashTableTest {

    @Test
    public void testPutAndGet() {
        HashTable<String, Integer> table = new HashTable<>();
        table.put("one", 1);
        table.put("two", 2);
        table.put("three", 3);
        assertEquals(1, table.get("one"));
        assertEquals(2, table.get("two"));
        assertEquals(3, table.get("three"));
        assertNull(table.get("four"));
    }

    @Test
    public void testPutOverwrite() {
        HashTable<String, Integer> table = new HashTable<>();

        table.put("key", 1);
        assertEquals(1, table.get("key"));

        table.put("key", 2);
        assertEquals(2, table.get("key"));
    }

    @Test
    public void testRemove() {
        HashTable<String, Integer> table = new HashTable<>();

        table.put("one", 1);
        table.put("two", 2);

        assertTrue(table.containsKey("one"));
        table.remove("one");
        assertFalse(table.containsKey("one"));
        assertTrue(table.containsKey("two"));
    }

    @Test
    public void testContainsKey() {
        HashTable<String, Integer> table = new HashTable<>();

        table.put("exists", 1);

        assertTrue(table.containsKey("exists"));
        assertFalse(table.containsKey("nonexistent"));
        assertFalse(table.containsKey(null));
    }

    @Test
    public void testResize() {
        HashTable<Integer, String> table = new HashTable<>();

        for (int i = 0; i < 20; i++) {
            table.put(i, "value" + i);
        }

        for (int i = 0; i < 20; i++) {
            assertEquals("value" + i, table.get(i));
        }
    }

    @Test
    public void testEquals() {
        HashTable<String, Integer> table1 = new HashTable<>();
        HashTable<String, Integer> table2 = new HashTable<>();
        final HashTable<String, Integer> table3 = new HashTable<>();

        table1.put("a", 1);
        table1.put("b", 2);

        table2.put("a", 1);
        table2.put("b", 2);

        table3.put("a", 1);
        table3.put("b", 3);

        assertEquals(table1, table2);
        assertNotEquals(table1, table3);
        assertEquals(table1, table1);
    }

    @Test
    public void testToString() {
        HashTable<String, Integer> table = new HashTable<>();
        table.put("a", 1);
        table.put("b", 2);

        String str = table.toString();
        assertTrue(str.contains("a=1"));
        assertTrue(str.contains("b=2"));
        assertTrue(str.startsWith("{"));
        assertTrue(str.endsWith("}"));
    }
}