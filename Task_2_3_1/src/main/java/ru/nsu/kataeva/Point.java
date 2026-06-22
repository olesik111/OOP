package ru.nsu.kataeva;

import java.util.Objects;

/**
 * Coordinate.
 */
public class Point {
    public final int xcoord;
    public final int ycoord;

    /**
     * Point constructor.
     *
     * @param xcoord x.
     * @param ycoord y.
     */
    public Point(int xcoord, int ycoord) {
        this.xcoord = xcoord;
        this.ycoord = ycoord;
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
        return xcoord == point.xcoord && ycoord == point.ycoord;
    }

    /**
     * Hash code.
     *
     * @return hash code of point.
     */
    @Override
    public int hashCode() {
        return Objects.hash(xcoord, ycoord);
    }




}
