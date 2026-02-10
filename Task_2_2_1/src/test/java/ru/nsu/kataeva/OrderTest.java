package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Order tests.
 */
class OrderTest {
    @Test
    void testOrder() {
        Order order = new Order(5);
        assertEquals(5, order.getId());
        assertDoesNotThrow(() -> order.setState("TEST"));
    }
}