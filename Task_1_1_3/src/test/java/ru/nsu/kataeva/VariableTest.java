package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Variable tests.
 */
class VariableTest {

    @Test
    void testVariableCreation() {
        Variable variable = new Variable("testVar");
        assertEquals("testVar", variable.toString());
    }

    @Test
    void testVariableDerivative() {
        Variable variableX = new Variable("x");

        Expression derivX = variableX.derivative("x");
        assertInstanceOf(Number.class, derivX);
        assertEquals(1.0, ((Number) derivX).getValue());

        Expression derivY = variableX.derivative("y");
        assertInstanceOf(Number.class, derivY);
        assertEquals(0.0, ((Number) derivY).getValue());
    }

    @Test
    void testVariableEval() {
        Variable variable = new Variable("x");

        double result = variable.eval("x=42.5; y=10");
        assertEquals(42.5, result);

        result = variable.eval("y=10; x=100");
        assertEquals(100.0, result);

        result = variable.eval("x = 7.3 ; y = 5");
        assertEquals(7.3, result);
    }

    @Test
    void testException() {
        Variable variable = new Variable("undefinedVar");

        assertThrows(IllegalArgumentException.class, () -> variable.eval("x=5; y=10"));

        assertThrows(IllegalArgumentException.class, () -> variable.eval(""));

        assertThrows(IllegalArgumentException.class, () -> variable.eval("bad format"));
    }

    @Test
    void testDoSimple() {
        Variable variable = new Variable("x");
        Expression simplified = variable.doSimple();
        assertSame(variable, simplified);
    }

}