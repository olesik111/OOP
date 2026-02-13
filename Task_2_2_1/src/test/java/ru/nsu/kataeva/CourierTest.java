package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Courier tests.
 */
class CourierTest {
    @Test
    void testCourier() throws InterruptedException {
        OrderQueue<Order> warehouse = new OrderQueue<>(5);
        Order order1 = new Order(1);
        Order order2 = new Order(2);
        warehouse.put(order1);
        warehouse.put(order2);

        Courier courier = new Courier(1, 2, warehouse);
        courier.start();
        Thread.sleep(1500);

        assertTrue(warehouse.isEmpty());
        assertEquals(States.DELIVERED, order1.getState());
        assertEquals(States.DELIVERED, order2.getState());

        courier.interrupt();
        courier.join();
    }
}
