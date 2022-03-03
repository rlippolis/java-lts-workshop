package com.jdriven.jdkworkshop.exercises.strings;

/**
 * More fun with some new String API methods since Java 11.
 */
public class FunWithStrings {

    /**
     * Use the {@link String#lines()} method to split the input text into separate lines and
     * return the average line size.
     */
    public double getAverageLineSize(String text) {
        return text.lines()
                .mapToInt(String::length)
                .average()
                .orElse(0);
    }

    /**
     * The String API now has a helper method to repeat a String a number of times: {@link String#repeat(int)}.
     * Use this to implement this method.
     */
    public String repeatMe(String text, int count) {
        return text.repeat(count);
    }
}
