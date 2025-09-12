package ru.nsu.kataeva;

/**
 * Represents multiplication operation between two expressions.
 */
public class Mul extends Expression {

    private final Expression left;
    private final Expression right;

    /**
     * Creates new multiplication expression.
     */
    public Mul(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Returns derivative of multiplication.
     */
    @Override
    public Expression derivative(String s) {
        return new Add(new Mul(left.derivative(s), right), new Mul(right.derivative(s), left));
    }

    /**
     * Evaluates multiplication result.
     */
    @Override
    public double eval(String s) {
        return left.eval(s) * right.eval(s);
    }

    /**
     * Simplifies multiplication expression.
     */
    @Override
    public Expression doSimple() {
        Expression simplifiedLeft = left.doSimple();
        Expression simplifiedRight = right.doSimple();

        if ((simplifiedLeft instanceof Number && ((Number) simplifiedLeft).getValue() == 0)
                || (simplifiedRight instanceof Number
                && ((Number) simplifiedRight).getValue() == 0)) {
            return new Number(0);
        }
        if (simplifiedRight instanceof Number && ((Number) simplifiedRight).getValue() == 1) {
            return simplifiedLeft;
        }
        if (simplifiedLeft instanceof Number && ((Number) simplifiedLeft).getValue() == 1) {
            return simplifiedRight;
        }

        if (simplifiedLeft instanceof Number
                && simplifiedRight instanceof Number) {
            return new Number(((Number) simplifiedLeft).getValue()
                    * ((Number) simplifiedRight).getValue());
        }

        return new Mul(simplifiedLeft, simplifiedRight);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "*" + right.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Mul a)) {
            return false;
        }
        return this.left.equals(a.left) && this.right.equals(a.right);
    }

    @Override
    public int hashCode() {
        return this.left.hashCode() + this.right.hashCode();
    }

}