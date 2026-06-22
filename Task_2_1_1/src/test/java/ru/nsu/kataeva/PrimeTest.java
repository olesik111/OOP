package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test for isPrime.
 */
public class PrimeTest {
    @Test
    void testIsPrime() {
        assertTrue(Prime.isPrime(2));
        assertTrue(Prime.isPrime(13));
        assertTrue(Prime.isPrime(10000019L));
    }

    @Test
    void testIsNotPrime() {
        assertFalse(Prime.isPrime(1));
        assertFalse(Prime.isPrime(4));
        assertFalse(Prime.isPrime(25));
    }
}
