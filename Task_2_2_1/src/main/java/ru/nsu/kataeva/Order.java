package ru.nsu.kataeva;

/**
 * Order class.
 */
public class Order {
    private final int id;
    private States state;

    /**
     * Constructor.
     *
     * @param id id.
     */
    public Order(int id) {
        this.id = id;
        this.state = States.CREATED;
    }

    /**
     * Set pizza state.
     *
     * @param stateNew to set.
     */
    public synchronized void setState(States stateNew) {
        this.state = stateNew;
        System.out.println("Order state " + this.id + " changed to " + stateNew);
    }

    /**
     * Get id.
     *
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Get state.
     *
     * @return state.
     */
    public synchronized States getState() {
        return state;
    }
}
