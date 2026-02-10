package ru.nsu.kataeva;

import java.util.ArrayList;
import java.util.List;

/**
 * Pizzeria class.
 */
public class Pizzeria {
    /**
     * Main class.
     * @param args args.
     */
    public static void main(String[] args) {
        PizzaLoader loader = new PizzaLoader();

        OrderQueue<Order> orders = new OrderQueue<>(-1);
        OrderQueue<Order> warehouse = new OrderQueue<>(loader.warehouseCapacity);

        List<Thread> staff = new ArrayList<>();

        for (int i = 0; i < loader.bakersCount; i++) {
            Baker b = new Baker(i + 1, 1000, orders, warehouse);
            staff.add(b);
            b.start();
        }

        for (int i = 0; i < loader.couriersCount; i++) {
            Courier c = new Courier(i + 1, i + 1, warehouse);
            staff.add(c);
            c.start();
        }

        Thread clientGenerator = new Thread(() -> {
            int id = 1;
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Order order = new Order(id++);
                    order.setState("CREATED");
                    orders.put(order);
                    Thread.sleep(700);
                }
            } catch (InterruptedException e) {
                System.out.println("ORDERS STOPPED");
            }
        });

        clientGenerator.start();

        try {
            System.out.println("=== START WORKING ===");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== END WORKING ===");

        clientGenerator.interrupt();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("=== CLOSED ===");
        for (Thread worker : staff) {
            worker.interrupt();
        }

    }
}
