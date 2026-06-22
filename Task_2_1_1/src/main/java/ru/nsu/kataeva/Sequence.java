package ru.nsu.kataeva;

/**
 * Checking prime numbers with sequence.
 */
public class Sequence {

    /**
     * Method for checking prime numbers with sequence.
     *
     * @param numbers - array of numbers.
     * @return - has prime or not.
     */
    public static boolean hasNotPrime(long[] numbers) {
        for (long number : numbers) {
            if (!Prime.isPrime(number)) {
                return true;
            }
        }
        return false;
    }
}
