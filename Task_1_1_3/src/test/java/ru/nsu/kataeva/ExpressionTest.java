package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Expression tests.
 */
class ExpressionTest {

    @Test
    void testComplex() {
        Expression expr = new Mul(
                new Add(new Variable("x"), new Number(5)),
                new Sub(new Variable("y"), new Number(3))
        );

        double result = expr.eval("x=2; y=10");
        assertEquals(49.0, result);
    }

    @Test
    void testDerivative() {
        Expression expr = new Add(
                new Mul(new Variable("x"), new Number(2)),
                new Div(new Number(3), new Variable("x"))
        );

        Expression derivative = expr.derivative("x");
        Expression expected = new Add(
                new Add(new Mul(new Number(1), new Number(2)),
                        new Mul(new Number(0), new Variable("x"))),
                new Div(
                        new Sub(
                                new Mul(new Number(0), new Variable("x")),
                                new Mul(new Number(1), new Number(3))
                        ),
                        new Mul(new Variable("x"), new Variable("x"))
                )
        );

        assertEquals(expected, derivative);
    }

    @Test
    void testSimplification() {
        Expression expr = new Add(
                new Mul(
                        new Add(new Variable("x"), new Number(0)),
                        new Mul(new Number(5), new Number(0))
                ),
                new Div(new Variable("y"), new Number(1))
        );

        Expression simplified = expr.doSimple();
        Expression expected = new Variable("y");

        assertEquals(expected, simplified);
    }
}