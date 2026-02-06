package ru.nsu.kataeva;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Checking prime numbers with threads.
 */
public class Threads {

    /**
     * Method for checking prime numbers with threads.
     *
     * @param numbers     - array of numbers.
     * @param threadCount - number of threads.
     * @return - has prime or not.
     * @throws InterruptedException - if an interruption was.
     */
    public static boolean hasNotPrime(long[] numbers, int threadCount) throws InterruptedException {
        AtomicBoolean found = new AtomicBoolean(false);
        Thread[] threads = new Thread[threadCount];
        int chunkSize = (numbers.length + threadCount - 1) / threadCount;
        for (int i = 0; i < threadCount; i++) {

            final int start = i * chunkSize;
            final int end = Math.min(start + chunkSize, numbers.length);

            threads[i] = new Thread(() -> {
                for (int j = start; j < end && !found.get(); j++) {
                    if (notPrime(numbers[j])) {
                        found.set(true);
                        break;
                    }
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) t.join();
        return found.get();
    }

    /**
     * Checking isf certain number is prime.
     *
     * @param number - to check.
     * @return - prime or not.
     */
    private static boolean notPrime(long number) {
        if (number == 1) {
            return true;
        }
        for (long i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return true;
            }
        }
        return false;
    }
}
