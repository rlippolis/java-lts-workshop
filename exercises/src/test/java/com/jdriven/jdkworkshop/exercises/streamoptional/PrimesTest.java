package com.jdriven.jdkworkshop.exercises.streamoptional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class PrimesTest {

    private static final List<Integer> PRIMES_SMALLER_THAN_100 = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97);
    private static final List<Integer> PRIMES_BETWEEN_50_AND_100 = PRIMES_SMALLER_THAN_100.stream().filter(x -> x >= 50).collect(Collectors.toList());

    private final Primes primes = new Primes();

    @Test
    public void testGetPrimesGeneratesAStreamOfPrimes() {
        assertThat(primes.getPrimes()
                    .limit(PRIMES_SMALLER_THAN_100.size()))
                .containsExactlyElementsOf(PRIMES_SMALLER_THAN_100);
    }

    @Test
    public void testGetPrimesUpUntilMaxiumumGeneratesALimitedStreamOfPrimes() {
        assertThat(primes.getPrimesUpUntil(2)).containsExactly(2);
        assertThat(primes.getPrimesUpUntil(100)).containsExactlyElementsOf(PRIMES_SMALLER_THAN_100);
    }

    @Test
    public void testGetPrimesStartingFromMinimumGeneratesALimitedStreamOfPrimes() {
        assertThat(primes.getPrimesStartingFrom(2).limit(1)).containsExactly(2);
        assertThat(primes.getPrimesStartingFrom(50)
                    .limit(PRIMES_BETWEEN_50_AND_100.size()))
                .containsExactlyElementsOf(PRIMES_BETWEEN_50_AND_100);
    }

}
