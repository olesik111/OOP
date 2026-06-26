package ru.nsu.kataeva;

import java.util.Optional;

/**
 * Baker class.
 */
public class Baker extends Thread {
    private final int speed;
    private final OrderQueue<Order> orders;
    private final OrderQueue<Order> warehouse;

    /**
     * Constructor.
     *
     * @param id id.
     * @param speed speed of doing pizza.
     * @param orders list of orders.
     * @param warehouse to give pizza to warehouse.
     */
    public Baker(int id, int speed, OrderQueue<Order> orders, OrderQueue<Order> warehouse) {
        super("Baker-" + id);
        this.speed = speed;
        this.orders = orders;
        this.warehouse = warehouse;
    }

    /**
     * Take pizza - cook - to warehouse.
     */
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Optional<Order> orderOpt = orders.take();
                if (orderOpt.isEmpty()) {
                    break;
                }
                Order order = orderOpt.get();
                order.setState(OrderState.BAKING);
                Thread.sleep(speed);
                order.setState(OrderState.READY);
                warehouse.put(order, () -> order.setState(OrderState.IN_WAREHOUSE));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(getName() + " went away");
        }
    }
}
