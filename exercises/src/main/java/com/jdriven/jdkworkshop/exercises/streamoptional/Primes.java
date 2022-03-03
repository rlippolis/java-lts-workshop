package com.jdriven.jdkworkshop.exercises.streamoptional;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;


import com.jdriven.jdkworkshop.util.TODO;

public class Primes {

    /**
     * Use the {@link Stream#iterate(Object, UnaryOperator)} method to generate a stream of prime numbers
     * (starting from the first prime, 2).
     * To check whether an integer is a prime, use the supplied {@link Primes#isPrime(int)} method.
     */
    public Stream<Integer> getPrimes() {
        return TODO.implementMe();
    }

    /**
     * Use the {@link Stream#takeWhile(Predicate)} method, in combination with the previous {@link Primes#getPrimes()}
     * method, to generate a Stream of prime numbers up until the supplied maximum number (inclusive).
     */
    public Stream<Integer> getPrimesUpUntil(int max) {
        return TODO.implementMe();
    }

    /**
     * Use the {@link Stream#dropWhile(Predicate)} method, in combination with the previous {@link Primes#getPrimes()}
     * method, to generate a Stream of prime numbers starting from the supplied minimum number (inclusive).
     */
    public Stream<Integer> getPrimesStartingFrom(int min) {
        return TODO.implementMe();
    }

    // ------------------------------------------------------------------------------------

    // Borrowed from: https://www.mkyong.com/java/how-to-determine-a-prime-number-in-java/
    private boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        //check if n is a multiple of 2
        if (n % 2 == 0) return false;
        //if not, then just check the odds
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}
