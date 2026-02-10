package ru.nsu.kataeva;

import java.util.List;

/**
 * Courier class.
 */
public class Courier extends Thread {
    private final int backpackCapacity;
    private final OrderQueue<Order> warehouse;

    /**
     * Constructor.
     *
     * @param id id.
     * @param backpackCapacity capacity.
     * @param warehouse to took from.
     */
    public Courier(int id, int backpackCapacity, OrderQueue<Order> warehouse) {
        super("Courier-" + id);
        this.backpackCapacity = backpackCapacity;
        this.warehouse = warehouse;
    }

    /**
     * Take and deliever.
     */
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                List<Order> items = warehouse.takeTheMost(backpackCapacity);
                for (Order item : items) {
                    item.setState("DELIVIRING");
                }
                Thread.sleep(1000);
                for (Order item : items) {
                    item.setState("DELIVIRED");
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Courier went away.");
        }
    }
}
