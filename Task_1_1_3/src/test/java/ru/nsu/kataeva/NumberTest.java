package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

/**
 * Number tests.
 */
class NumberTest {

    @Test
    void testNumberCreationAndGetters() {
        Number number = new Number(42.5);
        assertEquals(42.5, number.getValue(), 0.001);
    }

    @Test
    void testNumberDerivative() {
        Number number = new Number(10.0);
        Expression derivative = number.derivative("x");
        assertInstanceOf(Number.class, derivative);
        assertEquals(0.0, ((Number) derivative).getValue(), 0.001);
    }

    @Test
    void testNumberEval() {
        Number number = new Number(7.3);
        double result = number.eval("x=5; y=10");
        assertEquals(7.3, result, 0.001);
    }

    @Test
    void testNumberDoSimple() {
        Number number = new Number(15.0);
        Expression simplified = number.doSimple();
        assertSame(number, simplified);
    }

    @Test
    void testNumberToString() {
        Number number = new Number(123.456);
        assertEquals("123.456", number.toString());

        Number integerNumber = new Number(42);
        assertEquals("42.0", integerNumber.toString());
    }

    @Test
    void testNumberEqualsAndHashCode() {
        Number num1 = new Number(10.0);
        Number num2 = new Number(10.0);
        Number num3 = new Number(20.0);
        Number num4 = new Number(10.000001);

        assertEquals(num1, num2);
        assertNotEquals(num1, num3);
        assertNotEquals(num1, num4);
        assertEquals(num1.hashCode(), num2.hashCode());
        assertNotEquals(num1.hashCode(), num3.hashCode());

        assertNotEquals(null, num1);
    }
}