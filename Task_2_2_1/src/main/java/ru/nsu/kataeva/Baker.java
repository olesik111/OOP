package ru.nsu.kataeva;

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
                Order order = orders.take();
                if (order == null) {
                    break;
                }
                order.setState(States.BAKING);
                Thread.sleep(speed);
                order.setState(States.READY);
                warehouse.put(order, () -> order.setState(States.IN_WAREHOUSE));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }finally {
            System.out.println(getName() + " went away");
        }
    }

}
