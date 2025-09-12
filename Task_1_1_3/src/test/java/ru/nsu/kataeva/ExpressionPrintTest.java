package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Expression print tests.
 */
class ExpressionPrintTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testNumberPrint() {
        Number number = new Number(42.5);
        number.print();
        assertEquals("42.5" + System.lineSeparator(), outContent.toString());
    }

    @Test
    void testVariablePrint() {
        Variable variable = new Variable("testVar");
        variable.print();
        assertEquals("testVar" + System.lineSeparator(), outContent.toString());
    }

    @Test
    void testAddPrint() {
        Add add = new Add(new Number(2), new Number(3));
        add.print();
        assertEquals("(2.0+3.0)" + System.lineSeparator(), outContent.toString());
    }

    @Test
    void testSubPrint() {
        Sub sub = new Sub(new Number(10), new Number(3));
        sub.print();
        assertEquals("(10.0-3.0)" + System.lineSeparator(), outContent.toString());
    }

    @Test
    void testMulPrint() {
        Mul mul = new Mul(new Number(4), new Number(3));
        mul.print();
        assertEquals("(4.0*3.0)" + System.lineSeparator(), outContent.toString());
    }

    @Test
    void testDivPrint() {
        Div div = new Div(new Number(10), new Number(2));
        div.print();
        assertEquals("(10.0/2.0)" + System.lineSeparator(), outContent.toString());
    }

    @Test
    void testComplex() {
        Expression expr = new Mul(
                new Add(new Variable("x"), new Number(5)),
                new Sub(new Variable("y"), new Number(3))
        );

        expr.print();
        assertEquals("((x+5.0)*(y-3.0))" + System.lineSeparator(), outContent.toString());
    }

    @Test
    void testNested() {
        Expression expr = new Sub(
                new Add(
                        new Mul(new Variable("x"), new Number(2)),
                        new Div(new Number(3), new Variable("x"))
                ),
                new Number(1)
        );

        expr.print();
        assertEquals("(((x*2.0)+(3.0/x))-1.0)" + System.lineSeparator(), outContent.toString());
    }
}