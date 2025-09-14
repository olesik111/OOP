package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Div tests.
 */
class DivTest {

    @Test
    void testDivCreation() {
        Expression left = new Number(10);
        Expression right = new Number(2);
        Div div = new Div(left, right);

        assertEquals("(10.0/2.0)", div.toString());
    }

    @Test
    void testDivEval() {
        Div div = new Div(new Number(10), new Number(2));
        double result = div.eval("x=1");
        assertEquals(5.0, result, 0.001);

        Div withVariables = new Div(new Variable("x"), new Number(2));
        result = withVariables.eval("x=10");
        assertEquals(5.0, result, 0.001);
    }

    @Test
    void testException() {
        Div divByZero = new Div(new Number(1), new Number(0));

        assertThrows(ArithmeticException.class, () -> divByZero.eval("x=1"));

        Div divByVariableZero = new Div(new Number(1), new Variable("x"));

        assertThrows(ArithmeticException.class, () -> divByVariableZero.eval("x=0"));
    }

    @Test
    void testDivDerivative() {
        Div div = new Div(new Variable("x"), new Number(2));
        Expression derivative = div.derivative("x");

        Expression numerator = new Sub(
                new Mul(new Number(1), new Number(2)),
                new Mul(new Number(0), new Variable("x"))
        );
        Expression denominator = new Mul(new Number(2), new Number(2));
        Expression expected = new Div(numerator, denominator);

        assertEquals(expected, derivative);
    }

    @Nested
    class testDivDoSimple {
        @Test
        void numeric() {
            Div numericDiv = new Div(new Number(10), new Number(2));
            Expression simplified = numericDiv.doSimple();
            assertEquals(new Number(5), simplified);
        }

        @Test
        void zeroUp() {
            Div zeroNumerator = new Div(new Number(0), new Variable("x"));
            assertEquals(new Number(0), zeroNumerator.doSimple());
        }

        @Test
        void one() {
            Div denominatorOne = new Div(new Variable("x"), new Number(1));
            assertEquals(new Variable("x"), denominatorOne.doSimple());
        }

        @Test
        void zeroDown() {
            Div divByZero = new Div(new Number(1), new Number(0));
            assertThrows(ArithmeticException.class, divByZero::doSimple);
        }

        @Test
        void comp() {
            Div complex = new Div(new Variable("x"), new Variable("y"));
            Expression complexSimplified = complex.doSimple();
            assertEquals(complex, complexSimplified);
        }
    }

    @Test
    void testDivEquals() {
        Div div1 = new Div(new Number(4), new Number(2));
        Div div2 = new Div(new Number(4), new Number(2));
        Div div3 = new Div(new Number(4), new Number(3));
        Div div4 = new Div(new Number(6), new Number(2));

        assertEquals(div1, div2);
        assertNotEquals(div1, div3);
        assertNotEquals(div1, div4);
        assertEquals(div1.hashCode(), div2.hashCode());
        assertNotEquals(div1.hashCode(), div3.hashCode());
    }
}