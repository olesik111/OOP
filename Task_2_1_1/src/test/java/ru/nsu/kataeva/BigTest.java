package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BigTest {
    private final long[] primes = {
        10000019L, 10000079L, 10000103L, 10000139L, 10000141L,
        10000169L, 10000189L, 10000223L, 10000229L, 10000247L,
        10000253L, 10000261L, 10000271L, 10000303L, 10000339L
    };

    private long[] testData;

    @BeforeEach
    void setup() {
        int size = 10000;
        testData = new long[size];
        for (int i = 0; i < size; i++) {
            testData[i] = primes[i % primes.length];
        }
    }

    @Test
    void allPrimeSeqTest() {
        long start = System.nanoTime();
        boolean result = Sequence.hasNotPrime(testData);
        long end = System.nanoTime();
        assertFalse(result);
        System.out.println("Sequential: " + (end - start));
    }

    @Test
    void allPrimesThrFourTest() throws InterruptedException {
        long start = System.nanoTime();
        boolean result = Threads.hasNotPrime(testData, 4);
        long end = System.nanoTime();
        assertFalse(result);
        System.out.println("Threaded 4: " + (end - start));
    }

    @Test
    void allPrimesThrEightTest() throws InterruptedException {
        long start = System.nanoTime();
        boolean result = Threads.hasNotPrime(testData, 8);
        long end = System.nanoTime();
        assertFalse(result);
        System.out.println("Threaded 8: " + (end - start));
    }

    @Test
    void allPrimesParallStTest() {
        long start = System.nanoTime();
        boolean result = Streams.hasNotPrime(testData);
        long end = System.nanoTime();
        assertFalse(result);
        System.out.println("Parallel Stream: " + (end - start));
    }
}