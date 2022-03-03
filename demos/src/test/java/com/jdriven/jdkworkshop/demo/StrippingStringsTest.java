package com.jdriven.jdkworkshop.demo;

import org.junit.jupiter.api.Test;

/**
 * Java 11 has introduced a few new methods for stripping whitespace from a String,
 * and checking whether a String only contains whitespace:
 * - {@link String#isBlank()}
 * - {@link String#strip()}
 * - {@link String#stripLeading()}
 * - {@link String#stripTrailing()}
 *
 * The String API already has a {@link String#trim()} method, and a {@link String#isEmpty()} method (since Java 1.6).
 * However, these are a bit outdated. There are a few Unicode whitespace characters which are not recognized
 * by these outdated methods as whitespace. The traditional way of checking for whitespace is matching
 * every character with a codepoint lower than or equal to 'U+0020' (space character).
 * The new methods use the {@link Character#isWhitespace(char)} method, which is more accurate.
 *
 * In the class below we demonstrate the differences. Read the code in the unit test and run it, check the console output,
 * trying to understand what is shown.
 */
@SuppressWarnings("ConstantConditions")
public class StrippingStringsTest {

    @Test
    public void strippingStrings() {
        String traditionalWhitespace = "    ";
        String specialWhitespace = "\u2000\u2000\u2000\u2000";

        // Notice the difference in results between the existing and the new method
        // for checking if a String contains only whitespace
        System.out.println("Pre Java 11 : traditionalWhitespace.trim().isEmpty() ? " + traditionalWhitespace.trim().isEmpty());
        System.out.println("Java 11     : traditionalWhitespace.isBlank()        ? " + traditionalWhitespace.isBlank());
        System.out.println("Pre Java 11 : specialWhitespace.trim().isEmpty()     ? " + specialWhitespace.trim().isEmpty());
        System.out.println("Java 11     : specialWhitespace.isBlank()            ? " + specialWhitespace.isBlank());
        System.out.println();

        String textWithTraditionalWhitespace = traditionalWhitespace + "text" + traditionalWhitespace;
        String textWithSpecialWhitespace = specialWhitespace + "text" + specialWhitespace;

        // Notice that when we print these Strings to the console, they both contain the word 'text' surrounded by whitespace
        System.out.printf("Traditional whitespace : \"%s\"%n", textWithTraditionalWhitespace);
        System.out.printf("Special whitespace     : \"%s\"%n", textWithSpecialWhitespace);
        System.out.println();

        // Strip all whitespace:
        System.out.printf("Traditional whitespace stripped : \"%s\"%n", textWithTraditionalWhitespace.strip());
        System.out.printf("Special whitespace stripped     : \"%s\"%n", textWithSpecialWhitespace.strip());
        System.out.println();

        // Strip leading whitespace, notice how it handles special whitespace without any problem
        System.out.printf("Strip leading  : \"%s\"%n", textWithSpecialWhitespace.stripLeading());
        // Strip trailing whitespace:
        System.out.printf("Strip trailing : \"%s\"%n", textWithSpecialWhitespace.stripTrailing());
    }
}
