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

    /**
     * Constructor.
     *
     * @param capacity cap.
     */
    public OrderQueue(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Put pizza in queue.
     *
     * @param t type.
     * @throws InterruptedException exc.
     */
    public synchronized void put(T t) throws InterruptedException {
        while (queue.size() >= capacity && capacity > 0) {
            wait();
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
     * Is empty.
     *
     * @return if empty.
     */
    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
