package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Parser tests.
 */
class ParserTest {

    @Test
    void testParseNumber() {
        Expression result = Parser.parseString("42.5");
        assertInstanceOf(Number.class, result);
        assertEquals(42.5, ((Number) result).getValue(), 0.001);
    }

    @Test
    void testParseVariable() {
        Expression result = Parser.parseString("testVariable");
        assertInstanceOf(Variable.class, result);
        assertEquals("testVariable", result.toString());
    }

    @Test
    void testParseAddition() {
        Expression result = Parser.parseString("(x+5)");
        assertInstanceOf(Add.class, result);
        assertEquals("(x+5.0)", result.toString());
    }

    @Test
    void testParseComplex() {
        Expression result = Parser.parseString("((x*2)+(y/3))");
        assertInstanceOf(Add.class, result);
        assertEquals("((x*2.0)+(y/3.0))", result.toString());
    }

    @Test
    void testParseWhitespace() {
        Expression result = Parser.parseString(" ( x + 5 ) ");
        assertInstanceOf(Add.class, result);
        assertEquals("(x+5.0)", result.toString());
    }

    @Test
    void testParseException() {
        assertThrows(IllegalArgumentException.class, () -> Parser.parseString("(x^2)"));
    }

    @Test
    void testParseNegative() {
        Expression result = Parser.parseString("-5.5");
        assertInstanceOf(Number.class, result);
        assertEquals(-5.5, ((Number) result).getValue());
    }

}