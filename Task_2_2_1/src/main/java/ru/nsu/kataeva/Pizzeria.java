package ru.nsu.kataeva;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Pizzeria class.
 */
public class Pizzeria {

    private static final int INFINITE_CAPACITY = -1;

    /**
     * Main class.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        PizzaLoader loader;

        try (Reader reader = new FileReader("config.json")) {
            loader = PizzaLoader.load(reader);
        } catch (IOException e) {
            System.err.println("Problem with config file: " + e.getMessage());
            return;
        }

        OrderQueue<Order> orders = new OrderQueue<>(INFINITE_CAPACITY);
        OrderQueue<Order> warehouse = new OrderQueue<>(loader.warehouseCapacity);

        List<Thread> bakers = new ArrayList<>();
        List<Thread> couriers = new ArrayList<>();

        for (PizzaLoader.BakerConfig bakerConfig : loader.bakers) {
            Baker b = new Baker(bakerConfig.id, bakerConfig.speed, orders, warehouse);
            bakers.add(b);
            b.start();
        }

        for (PizzaLoader.CourierConfig courierConfig : loader.couriers) {
            Courier c = new Courier(courierConfig.id, courierConfig.backpackCapacity, warehouse);
            couriers.add(c);
            c.start();
        }

        Thread clientGenerator = new Thread(() -> {
            int id = 1;
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Order order = new Order(id++);
                    orders.put(order);
                    Thread.sleep(700);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        clientGenerator.start();

        try {
            System.out.println("=== START WORKING ===");
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("=== SOON WE WILL FINISH WORKING ===");

        clientGenerator.interrupt();

        orders.close();
        for (Thread b : bakers) {
            try {
                b.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        warehouse.close();
        for (Thread c : couriers) {
            try {
                c.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("=== PIZZERIA CLOSED ===");
    }
}