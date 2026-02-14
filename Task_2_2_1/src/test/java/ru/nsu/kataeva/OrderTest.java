package ru.nsu.kataeva;

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
        assertEquals(OrderState.CREATED, order.getState());

        order.setState(OrderState.BAKING);
        assertEquals(OrderState.BAKING, order.getState());
    }
}