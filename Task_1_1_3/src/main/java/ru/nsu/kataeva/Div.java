package ru.nsu.kataeva;

/**
 * Represents division operation between two expressions.
 */
public class Div extends Expression {

    private final Expression left;
    private final Expression right;

    /**
     * Creates new division expression.
     */
    public Div(Expression left, Expression right) {
        this.left = left; //up
        this.right = right; //down
    }

    /**
     * Returns derivative of division.
     */
    @Override
    public Expression derivative(String s) {
        return new Div(new Sub(new Mul(left.derivative(s), right),
                new Mul(right.derivative(s), left)), new Mul(right, right));
    }

    /**
     * Evaluates division result.
     */
    @Override
    public double eval(String s) {
        double check = right.eval(s);
        if (check == 0) {
            throw new ArithmeticException("division by zero.");
        }
        return left.eval(s) / check;
    }

    /**
     * Simplifies division expression.
     */
    @Override
    public Expression doSimple() {
        Expression simplifiedLeft = left.doSimple();
        Expression simplifiedRight = right.doSimple();

        if (simplifiedLeft instanceof Number && ((Number) simplifiedLeft).getValue() == 0) {
            return new Number(0);
        }
        if (simplifiedRight instanceof Number && ((Number) simplifiedRight).getValue() == 0) {
            throw new ArithmeticException("division by zero.");
        }
        if (simplifiedRight instanceof Number && ((Number) simplifiedRight).getValue() == 1) {
            return simplifiedLeft;
        }

        if (simplifiedLeft instanceof Number && simplifiedRight instanceof Number) {
            return new Number(((Number) simplifiedLeft).getValue() / ((Number) simplifiedRight).getValue());
        }

        return new Div(simplifiedLeft, simplifiedRight);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "/" + right.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Div a)) {
            return false;
        }
        return this.left.equals(a.left) && this.right.equals(a.right);
    }

    @Override
    public int hashCode() {
        return this.left.hashCode() + this.right.hashCode();
    }

}