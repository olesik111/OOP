package ru.nsu.kataeva;

import java.util.List;

/**
 * Courier class.
 */
public class Courier extends Thread {
    private final int backpackCapacity;
    private final OrderQueue<Order> warehouse;
    private static final int DELIVERY_TIME_MS = 1000;

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
                if (items.isEmpty()) {
                    break;
                }
                for (Order item : items) {
                    item.setState(OrderState.DELIVERING);
                }
                Thread.sleep(DELIVERY_TIME_MS);
                for (Order item : items) {
                    item.setState(OrderState.DELIVERED);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(getName() + " went away");
        }
    }
}
