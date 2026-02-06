package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests for checking correctness.
 */
public class BasicTests {
    @Test
    void testEmptyArray() throws InterruptedException {
        long[] empty = {};
        assertFalse(Sequence.hasNotPrime(empty));
        assertFalse(Threads.hasNotPrime(empty, 4));
        assertFalse(Streams.hasNotPrime(empty));
    }

    @Test
    void testOnlyPrimes() throws InterruptedException {
        long[] primes = {2, 3, 5, 7, 11, 13, 17};
        assertFalse(Sequence.hasNotPrime(primes));
        assertFalse(Threads.hasNotPrime(primes, 4));
        assertFalse(Streams.hasNotPrime(primes));
    }

    @Test
    void testContainsNotPrime() throws InterruptedException {
        long[] mixed = {2, 3, 4, 5, 7};
        assertTrue(Sequence.hasNotPrime(mixed));
        assertTrue(Threads.hasNotPrime(mixed, 2));
        assertTrue(Streams.hasNotPrime(mixed));
    }

    @Test
    void testSmallArrayLargeThreads() throws InterruptedException {
        long[] small = {2, 10};
        assertTrue(Threads.hasNotPrime(small, 4));

        long[] smallPrimes = {2, 3};
        assertFalse(Threads.hasNotPrime(smallPrimes, 4));
    }

    @Test
    void testSingleElement() throws InterruptedException {
        long[] one = {1};
        assertTrue(Sequence.hasNotPrime(one));
        assertTrue(Threads.hasNotPrime(one, 4));

        long[] onePrime = {7};
        assertFalse(Sequence.hasNotPrime(onePrime));
    }
}
