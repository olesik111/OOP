package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Baker tests.
 */
class BakerTest {
    @Test
    void testBaker() throws InterruptedException {
        OrderQueue<Order> orders = new OrderQueue<>(5);
        OrderQueue<Order> warehouse = new OrderQueue<>(5);

        orders.put(new Order(1));

        Baker baker = new Baker(1, 0, orders, warehouse);
        baker.start();

        Thread.sleep(100);

        assertTrue(orders.isEmpty());
        assertFalse(warehouse.isEmpty());
        assertEquals(1, warehouse.take().getId());

        baker.interrupt();
    }
}
