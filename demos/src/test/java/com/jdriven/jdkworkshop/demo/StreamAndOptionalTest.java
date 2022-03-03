package com.jdriven.jdkworkshop.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

@SuppressWarnings({"unused", "ConstantConditions"})
public class StreamAndOptionalTest {

    /**
     * Demonstrate {@link Stream#ofNullable(Object)}.
     */
    @Test
    public void streamOfNullable() {
        try {
            Stream<String> streamCreatedByStreamOf = Stream.of((String) null);
        } catch (NullPointerException e) {
            // This exception is thrown because the Stream contains a null element
        }

        // No exception thrown because Stream.ofNullable creates an empty stream when the element is null
        Stream<String> streamCreatedByStreamOfNullable = Stream.ofNullable(null);
        streamCreatedByStreamOfNullable.forEach(s -> System.out.println(s.length()));
    }

    /**
     * Demonstrate {@link Optional#isEmpty()} which is the opposite of {@link Optional#isPresent()}.
     */
    @Test
    public void optionalIsEmpty() {
        Optional<String> emptyOptional = Optional.empty();
        assertThat(emptyOptional.isPresent()).isFalse();
        assertThat(emptyOptional.isEmpty()).isTrue(); // New in Java 11
    }

    /**
     * Demonstrate {@link Optional#orElseThrow()}.
     *
     * It provides the same functionality as {@link Optional#get()},
     * but the method name makes it more explicit that it might throw an exception when the optional is empty.
     * The {@link Optional#get()} method might be removed in a later version.
     */
    @Test
    public void optionalOrElseThrow() {
        Optional<String> optionalStr = Optional.of("Some text");

        String extractedUsingOptionalGet = optionalStr.get();
        String extractedUsingOptionalOrElseThrow = optionalStr.orElseThrow(); // New in Java 10

        assertThat(extractedUsingOptionalGet).isEqualTo(extractedUsingOptionalOrElseThrow);
    }
}
