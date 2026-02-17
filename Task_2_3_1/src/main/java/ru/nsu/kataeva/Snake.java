package ru.nsu.kataeva;

import java.util.ArrayList;
import java.util.List;

/**
 * Snake class.
 */
public class Snake {
    private final List<Point> body;

    /**
     * Constructor.
     *
     * @param pos position of head.
     */
    public Snake(Point pos) {
        body = new ArrayList<>();
        body.add(pos);
    }

    /**
     * Get head.
     *
     * @return head.
     */
    public Point getHead() {
        return body.get(0);
    }

    /**
     * Move to new cell.
     *
     * @param newHead new head.
     */
    public void move(Point newHead) {
        body.add(0, newHead);
        body.remove(body.size() - 1);
    }

    /**
     * Grow snake.
     *
     * @param newHead new head.
     */
    public void grow(Point newHead) {
        body.add(0, newHead);
    }

    /**
     * If snake body contains a point.
     *
     * @param p to check.
     * @return if contains.
     */
    public boolean contains(Point p) {
        return body.contains(p);
    }

    /**
     * Check for collision.
     *
     * @param newHead new head.
     * @return if collision.
     */
    public boolean collision(Point newHead) {
        for (int i = 0; i < body.size() - 1; i++) {
            if (body.get(i).equals(newHead)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get body.
     *
     * @return body.
     */
    public List<Point> getBody() {
        return body;
    }
}