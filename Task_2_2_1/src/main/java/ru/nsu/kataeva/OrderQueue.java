package ru.nsu.kataeva;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Queue.
 *
 * @param <T> for orders.
 */
public class OrderQueue<T> {
    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;
    private boolean closed = false;

    /**
     * Constructor.
     *
     * @param capacity cap.
     */
    public OrderQueue(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Put item in queue.
     *
     * @param t type.
     * @throws InterruptedException exc.
     */
    public synchronized void put(T t) throws InterruptedException {
        put(t, null);
    }

    /**
     * Put item in queue and action atomically.
     *
     * @param t type.
     * @param action before adding to queue.
     * @throws InterruptedException exc.
     */
    public synchronized void put(T t, Runnable action) throws InterruptedException {
        while (queue.size() >= capacity && capacity > 0) {
            wait();
        }
        if (action != null) {
            action.run();
        }
        queue.add(t);
        notifyAll();
    }

    /**
     * Take pizza from queue.
     *
     * @throws InterruptedException exc.
     */
    public synchronized T take() throws InterruptedException {
        while (queue.isEmpty()) {
            if (closed) {
                return null;
            }
            wait();
        }
        T t = queue.poll();
        notifyAll();
        return t;
    }

    /**
     * Take as much as can.
     *
     * @param limit max val.
     * @return list of taken.
     * @throws InterruptedException exc.
     */
    public synchronized List<T> takeTheMost(int limit) throws InterruptedException {
        while (queue.isEmpty()) {
            if (closed) {
                return new ArrayList<>();
            }
            wait();
        }
        List<T> list = new ArrayList<>();
        for (int i = 0; i < limit && !queue.isEmpty(); i++) {
            list.add(queue.poll());
        }
        notifyAll();
        return list;
    }

    /**
     * Pizzeria is closed. Finish the work.
     */
    public synchronized void close() {
        closed = true;
        notifyAll();
    }

    /**
     * Is empty.
     *
     * @return if empty.
     */
    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
