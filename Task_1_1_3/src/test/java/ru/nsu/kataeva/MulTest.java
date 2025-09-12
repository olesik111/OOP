package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * Mul tests.
 */
class MulTest {

    @Test
    void testMulCreation() {
        Expression left = new Number(4);
        Expression right = new Number(3);
        Mul mul = new Mul(left, right);

        assertEquals("(4.0*3.0)", mul.toString());
    }

    @Test
    void testMulEval() {
        Mul mul = new Mul(new Number(4), new Number(3));
        double result = mul.eval("x=1");
        assertEquals(12.0, result, 0.001);

        Mul withVariables = new Mul(new Variable("x"), new Number(2));
        result = withVariables.eval("x=5");
        assertEquals(10.0, result, 0.001);
    }

    @Test
    void testMulDerivative() {
        Mul mul = new Mul(new Variable("x"), new Variable("x"));
        Expression derivative = mul.derivative("x");

        Expression expected = new Add(
                new Mul(new Number(1), new Variable("x")),
                new Mul(new Number(1), new Variable("x"))
        );
        assertEquals(expected, derivative);
    }

    @Test
    void testMulDoSimple() {
        Mul numericMul = new Mul(new Number(4), new Number(3));
        Expression simplified = numericMul.doSimple();
        assertEquals(new Number(12), simplified);

        Mul withZeroLeft = new Mul(new Number(0), new Variable("x"));
        assertEquals(new Number(0), withZeroLeft.doSimple());

        Mul withZeroRight = new Mul(new Variable("x"), new Number(0));
        assertEquals(new Number(0), withZeroRight.doSimple());

        Mul withOneRight = new Mul(new Variable("x"), new Number(1));
        assertEquals(new Variable("x"), withOneRight.doSimple());

        Mul withOneLeft = new Mul(new Number(1), new Variable("x"));
        assertEquals(new Variable("x"), withOneLeft.doSimple());

        Mul complex = new Mul(new Variable("x"), new Variable("y"));
        Expression complexSimplified = complex.doSimple();
        assertEquals(complex, complexSimplified);
    }

    @Test
    void testMulEqualsAndHashCode() {
        Mul mul1 = new Mul(new Number(2), new Number(3));
        Mul mul2 = new Mul(new Number(2), new Number(3));
        Mul mul3 = new Mul(new Number(2), new Number(4));
        Mul mul4 = new Mul(new Number(3), new Number(3));

        assertEquals(mul1, mul2);
        assertNotEquals(mul1, mul3);
        assertNotEquals(mul1, mul4);
        assertEquals(mul1.hashCode(), mul2.hashCode());
        assertNotEquals(mul1.hashCode(), mul3.hashCode());
    }
}