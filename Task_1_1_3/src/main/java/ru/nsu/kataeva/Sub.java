package ru.nsu.kataeva;

/**
 * Represents subtraction operation between two expressions.
 */
public class Sub extends Expression {

    private final Expression left;
    private final Expression right;

    /**
     * Creates new subtraction expression.
     */
    public Sub(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Returns derivative of subtraction.
     */
    @Override
    public Expression derivative(String s) {
        return new Sub(left.derivative(s), right.derivative(s));
    }

    /**
     * Evaluates subtraction result.
     */
    @Override
    public double eval(String s) {
        return left.eval(s) - right.eval(s);
    }

    /**
     * Simplifies subtraction expression.
     */
    @Override
    public Expression doSimple() {
        Expression simplifiedLeft = left.doSimple();
        Expression simplifiedRight = right.doSimple();

        if (simplifiedLeft instanceof Number && simplifiedRight instanceof Number) {
            return new Number(((Number) simplifiedLeft).getValue()
                    - ((Number) simplifiedRight).getValue());
        }

        if (simplifiedLeft instanceof Number && ((Number) simplifiedLeft).getValue() == 0) {
            return simplifiedRight;
        }
        if (simplifiedRight instanceof Number && ((Number) simplifiedRight).getValue() == 0) {
            return simplifiedLeft;
        }

        return new Sub(simplifiedLeft, simplifiedRight);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "-" + right.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sub a)) {
            return false;
        }
        return this.left.equals(a.left) && this.right.equals(a.right);
    }

    @Override
    public int hashCode() {
        return this.left.hashCode() + this.right.hashCode();
    }

}