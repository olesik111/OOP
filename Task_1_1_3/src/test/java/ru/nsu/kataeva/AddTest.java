package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * Add tests.
 */
class AddTest {

    @Test
    void testAddCreation() {
        Expression left = new Number(5);
        Expression right = new Number(3);
        Add add = new Add(left, right);

        assertEquals("(5.0+3.0)", add.toString());
    }

    @Test
    void testAddEval() {
        Add add = new Add(new Number(2), new Number(3));
        double result = add.eval("x=1");
        assertEquals(5.0, result, 0.001);

        Add withVariables = new Add(new Variable("x"), new Number(5));
        result = withVariables.eval("x=10");
        assertEquals(15.0, result, 0.001);
    }

    @Test
    void testAddDerivative() {
        Add add = new Add(new Variable("x"), new Number(5));
        Expression derivative = add.derivative("x");

        assertInstanceOf(Add.class, derivative);
        assertEquals(new Add(new Number(1), new Number(0)), derivative);
    }

    @Test
    void testAddDoSimple() {
        Add numericAdd = new Add(new Number(2), new Number(3));
        Expression simplified = numericAdd.doSimple();
        assertEquals(new Number(5), simplified);

        Add withZeroLeft = new Add(new Number(0), new Variable("x"));
        assertEquals(new Variable("x"), withZeroLeft.doSimple());

        Add withZeroRight = new Add(new Variable("x"), new Number(0));
        assertEquals(new Variable("x"), withZeroRight.doSimple());

        Add complex = new Add(new Variable("x"), new Variable("y"));
        Expression complexSimplified = complex.doSimple();
        assertEquals(complex, complexSimplified);
    }

    @Test
    void testAddEqualsAndHashCode() {
        Add add1 = new Add(new Number(1), new Number(2));
        Add add2 = new Add(new Number(1), new Number(2));
        Add add3 = new Add(new Number(1), new Number(3));
        Add add4 = new Add(new Number(3), new Number(2));

        assertEquals(add1, add2);
        assertNotEquals(add1, add3);
        assertNotEquals(add1, add4);
        assertEquals(add1.hashCode(), add2.hashCode());
        assertNotEquals(add1.hashCode(), add3.hashCode());
    }
}