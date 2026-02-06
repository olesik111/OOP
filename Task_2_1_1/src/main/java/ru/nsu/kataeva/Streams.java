package ru.nsu.kataeva;

import java.util.Arrays;

/**
 * Checking prime numbers with parallel streams.
 */
public class Streams {

    /**
     * Method for checking prime numbers in array with parallel streams.
     *
     * @param numbers - array of numbers.
     * @return - has prime or not.
     */
    public static boolean hasNotPrime(long[] numbers) {
        return Arrays.stream(numbers)
                .parallel()
                .anyMatch(Streams::notPrime);
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
