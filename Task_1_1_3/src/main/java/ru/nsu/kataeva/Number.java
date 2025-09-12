package ru.nsu.kataeva;

/**
 * Represents a constant numerical value.
 */
public class Number extends Expression {

    private final double num;

    /**
     * Creates new number with given value.
     */
    public Number(double num) {
        this.num = num;
    }

    /**
     * Gets the numerical value.
     */
    public double getValue() {
        return num;
    }

    /**
     * Derivative of constant is always zero.
     */
    @Override
    public Expression derivative(String s) {
        return new Number(0);
    }

    /**
     * Returns the number value.
     */
    @Override
    public double eval(String s) {
        return num;
    }

    /**
     * Number is already simplified.
     */
    @Override
    public Expression doSimple() {
        return this;
    }

    @Override
    public String toString() {
        return String.valueOf(num);
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Number n)) {
            return false;
        }
        return n.num == num;
    }
}