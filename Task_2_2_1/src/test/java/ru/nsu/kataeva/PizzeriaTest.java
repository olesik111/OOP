package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

/**
 * Pizzeria test.
 */
class PizzeriaTest {
    @Test
    void testMain() {
        assertDoesNotThrow(() -> Pizzeria.main(new String[]{}));
    }
}