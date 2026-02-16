package ru.nsu.kataeva;

/**
 * Coordinate.
 */
public class Point {
    public final int xCoord;
    public final int yCoord;

    /**
     * Point constructor.
     *
     * @param xCoord x.
     * @param yCoord y.
     */
    public Point(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
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
        return xCoord == point.xCoord && yCoord == point.yCoord;
    }


}
