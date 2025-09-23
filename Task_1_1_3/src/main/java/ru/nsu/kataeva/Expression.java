package ru.nsu.kataeva;

/**
 * Base class for all mathematical expressions.
 */
public abstract class Expression {

    /**
     * Prints the expression.
     */
    public void print() {
        System.out.println(this);
    }

    /**
     * Calculates derivative of expression.
     */
    public abstract Expression derivative(String s);

    /**
     * Evaluates expression with given variables.
     */
    public abstract double eval(String s);

    /**
     * Simplifies the expression.
     */
    public abstract Expression doSimple();
}