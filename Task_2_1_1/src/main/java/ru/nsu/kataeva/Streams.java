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
                .anyMatch(number -> !Prime.isPrime(number));
    }
}
