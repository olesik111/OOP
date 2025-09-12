package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * Sub tests.
 */
class SubTest {

    @Test
    void testSubCreation() {
        Expression left = new Number(10);
        Expression right = new Number(3);
        Sub sub = new Sub(left, right);

        assertEquals("(10.0-3.0)", sub.toString());
    }

    @Test
    void testSubEval() {
        Sub sub = new Sub(new Number(10), new Number(3));
        double result = sub.eval("x=1");
        assertEquals(7.0, result, 0.001);

        Sub withVariables = new Sub(new Variable("x"), new Number(5));
        result = withVariables.eval("x=15");
        assertEquals(10.0, result, 0.001);
    }

    @Test
    void testSubDerivative() {
        Sub sub = new Sub(new Variable("x"), new Number(5));
        Expression derivative = sub.derivative("x");

        assertInstanceOf(Sub.class, derivative);
        assertEquals(new Sub(new Number(1), new Number(0)), derivative);
    }

    @Test
    void testSubDoSimple() {
        Sub numericSub = new Sub(new Number(10), new Number(3));
        Expression simplified = numericSub.doSimple();
        assertEquals(new Number(7), simplified);

        Sub withZeroRight = new Sub(new Variable("x"), new Number(0));
        assertEquals(new Variable("x"), withZeroRight.doSimple());
    }

    @Test
    void testSubEqualsAndHashCode() {
        Sub sub1 = new Sub(new Number(5), new Number(3));
        Sub sub2 = new Sub(new Number(5), new Number(3));
        Sub sub3 = new Sub(new Number(5), new Number(2));
        Sub sub4 = new Sub(new Number(4), new Number(3));

        assertEquals(sub1, sub2);
        assertNotEquals(sub1, sub3);
        assertNotEquals(sub1, sub4);
        assertEquals(sub1.hashCode(), sub2.hashCode());
        assertNotEquals(sub1.hashCode(), sub3.hashCode());
    }
}