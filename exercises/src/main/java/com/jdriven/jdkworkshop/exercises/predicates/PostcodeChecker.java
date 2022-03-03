package com.jdriven.jdkworkshop.exercises.predicates;

import static java.util.function.Predicate.not;

import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class PostcodeChecker {

    // Regular expression which can be used to validate whether a String matches a valid Dutch postal code
    private static final Pattern POSTCODE_REGEX_PATTERN = Pattern.compile("^[1-9][0-9]{3} ?[A-Z]{2}$");

    /**
     * Using the regular expression pattern defined above ({@link PostcodeChecker#POSTCODE_REGEX_PATTERN}),
     * create a predicate which verifies whether a given String is a valid postal code.
     *
     * TIP: the {@link Pattern#asMatchPredicate()} method can be used.
     */
    private Predicate<String> isValidPostcode() {
        return POSTCODE_REGEX_PATTERN.asMatchPredicate();
    }

    /**
     * Filters the incoming stream of Strings, returning a Stream which contains only valid postal codes.
     */
    public Stream<String> filterOnlyValidPostcodes(Stream<String> strings) {
        return strings.filter(isValidPostcode());
    }

    /**
     * Use the {@link Predicate#not(Predicate)} method to create an inverted predicate for filtering the incoming Stream.
     * The resulting Stream should keep only the elements which are not valid postal codes.
     */
    public Stream<String> filterOnlyInvalidPostcodes(Stream<String> strings) {
        return strings.filter(not(isValidPostcode()));
    }

}
