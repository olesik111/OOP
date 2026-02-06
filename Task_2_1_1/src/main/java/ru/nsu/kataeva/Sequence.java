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
            if (notPrime(number)) {
                return true;
            }
        }
        return false;
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
