package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Courier tests.
 */
class CourierTest {
    @Test
    void testCourier() throws InterruptedException {
        OrderQueue<Order> warehouse = new OrderQueue<>(5);
        warehouse.put(new Order(1));
        warehouse.put(new Order(2));

        Courier courier = new Courier(1, 2, warehouse);
        courier.start();

        Thread.sleep(1200);

        assertTrue(warehouse.isEmpty());

        courier.interrupt();
    }
}
