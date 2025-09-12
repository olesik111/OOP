package ru.nsu.kataeva;

/**
 * Represents addition operation between two expressions.
 */
public class Add extends Expression {

    private final Expression left;
    private final Expression right;

    /**
     * Creates new addition expression.
     */
    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Returns derivative of addition.
     */
    @Override
    public Expression derivative(String s) {
        return new Add(left.derivative(s), right.derivative(s));
    }

    /**
     * Evaluates addition result.
     */
    @Override
    public double eval(String s) {
        return left.eval(s) + right.eval(s);
    }

    /**
     * Simplifies addition expression.
     */
    @Override
    public Expression doSimple() {
        Expression simplifiedLeft = left.doSimple();
        Expression simplifiedRight = right.doSimple();

        if (simplifiedLeft instanceof Number && simplifiedRight instanceof Number) {
            return new Number(((Number) simplifiedLeft).getValue() + ((Number) simplifiedRight).getValue());
        }

        if (simplifiedLeft instanceof Number && ((Number) simplifiedLeft).getValue() == 0) {
            return simplifiedRight;
        }
        if (simplifiedRight instanceof Number && ((Number) simplifiedRight).getValue() == 0) {
            return simplifiedLeft;
        }

        return new Add(simplifiedLeft, simplifiedRight);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "+" + right.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Add a)) {
            return false;
        }
        return this.left.equals(a.left) && this.right.equals(a.right);
    }

    @Override
    public int hashCode() {
        return this.left.hashCode() + this.right.hashCode();
    }

}