package ru.nsu.kataeva;

public class Prime {

    /**
     * Checking if certain number is prime.
     *
     * @param number - to check.
     * @return - prime or not.
     */
    public static boolean isPrime(long number) {
        if (number == 1) {
            return false;
        }
        for (long i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
