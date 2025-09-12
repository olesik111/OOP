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
    void testParseSimpleAddition() {
        Expression result = Parser.parseString("(x+5)");
        assertInstanceOf(Add.class, result);
        assertEquals("(x+5.0)", result.toString());
    }

    @Test
    void testParseComplexExpression() {
        Expression result = Parser.parseString("((x*2)+(y/3))");
        assertInstanceOf(Add.class, result);
        assertEquals("((x*2.0)+(y/3.0))", result.toString());
    }

    @Test
    void testParseWithWhitespace() {
        Expression result = Parser.parseString(" ( x + 5 ) ");
        assertInstanceOf(Add.class, result);
        assertEquals("(x+5.0)", result.toString());
    }

    @Test
    void testParseThrowsExceptionForUnknownOperator() {
        assertThrows(IllegalArgumentException.class, () -> Parser.parseString("(x^2)"));
    }

    @Test
    void testParseNegativeNumbers() {
        Expression result = Parser.parseString("-5.5");
        assertInstanceOf(Number.class, result);
        assertEquals(-5.5, ((Number) result).getValue());
    }

    @Test
    void testParseNestedOperations() {
        Expression result = Parser.parseString("(((x+1)*2)-3)");
        assertInstanceOf(Sub.class, result);
        assertEquals("(((x+1.0)*2.0)-3.0)", result.toString());
    }
}