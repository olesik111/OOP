package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Queue tests.
 */
class OrderQueueTest {
    @Test
    void testPutAndTake() throws InterruptedException {
        OrderQueue<Order> queue = new OrderQueue<>(2);
        assertTrue(queue.isEmpty());

        Order order = new Order(1);
        queue.put(order);
        assertFalse(queue.isEmpty());

        Order taken = queue.take();
        assertEquals(order, taken);
        assertTrue(queue.isEmpty());
    }

    @Test
    void testTakeTheMost() throws InterruptedException {
        OrderQueue<Order> queue = new OrderQueue<>(5);
        queue.put(new Order(1));
        queue.put(new Order(2));
        queue.put(new Order(3));

        List<Order> list = queue.takeTheMost(2);
        assertEquals(2, list.size());
        assertFalse(queue.isEmpty());

        List<Order> remaining = queue.takeTheMost(5);
        assertEquals(1, remaining.size());
        assertTrue(queue.isEmpty());
    }
}