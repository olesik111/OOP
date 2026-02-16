package ru.nsu.kataeva;

/**
 * Coordinate.
 */
public class Point {
    public final int x;
    public final int y;

    /**
     * Point constructor.
     *
     * @param x x.
     * @param y y.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Equals.
     *
     * @param o   the reference object with which to compare.
     * @return if equals.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }


}
