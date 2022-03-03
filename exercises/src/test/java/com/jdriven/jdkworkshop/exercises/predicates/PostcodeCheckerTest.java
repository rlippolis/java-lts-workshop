package com.jdriven.jdkworkshop.exercises.predicates;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class PostcodeCheckerTest {

    private static final String VALID_POSTCODE_1 = "1234 AB";
    private static final String VALID_POSTCODE_2 = "4321 BA";
    private static final String VALID_POSTCODE_3 = "1111 CC";
    private static final String INVALID_POSTCODE_1 = "bla";
    private static final String INVALID_POSTCODE_2 = "alert(1);";
    private static final String INVALID_POSTCODE_3 = "Robert'); DROP TABLE Students;--";

    private final PostcodeChecker postcodeChecker = new PostcodeChecker();

    @Test
    public void testFilterOnlyValidPostcodesReturnsEmptyStreamWhenStreamWasEmpty() {
        assertThat(postcodeChecker.filterOnlyValidPostcodes(Stream.empty())).isEmpty();
    }

    @Test
    public void testFilterOnlyValidPostcodesReturnsEmptyStreamWhenStreamContainsOnlyInvalidPostcodes() {
        Stream<String> input = Stream.of(INVALID_POSTCODE_1, INVALID_POSTCODE_2, INVALID_POSTCODE_3);
        assertThat(postcodeChecker.filterOnlyValidPostcodes(input)).isEmpty();
    }

    @Test
    public void testFilterOnlyValidPostcodesReturnsStreamOfValidPostcodesWhenStreamContainsBothInvalidAndValidPostcodes() {
        Stream<String> input = Stream.of(VALID_POSTCODE_1, INVALID_POSTCODE_1, VALID_POSTCODE_2, INVALID_POSTCODE_2, VALID_POSTCODE_3, INVALID_POSTCODE_3);
        assertThat(postcodeChecker.filterOnlyValidPostcodes(input)).containsExactly(VALID_POSTCODE_1, VALID_POSTCODE_2, VALID_POSTCODE_3);
    }

    @Test
    public void testFilterOnlyInvalidPostcodesReturnsEmptyStreamWhenStreamWasEmpty() {
        assertThat(postcodeChecker.filterOnlyInvalidPostcodes(Stream.empty())).isEmpty();
    }

    @Test
    public void testFilterOnlyInvalidPostcodesReturnsEmptyStreamWhenStreamContainsOnlyValidPostcodes() {
        Stream<String> input = Stream.of(VALID_POSTCODE_1, VALID_POSTCODE_2, VALID_POSTCODE_3);
        assertThat(postcodeChecker.filterOnlyInvalidPostcodes(input)).isEmpty();
    }

    @Test
    public void testFilterOnlyInvalidPostcodesReturnsStreamOfInvalidPostcodesWhenStreamContainsBothInvalidAndValidPostcodes() {
        Stream<String> input = Stream.of(VALID_POSTCODE_1, INVALID_POSTCODE_1, VALID_POSTCODE_2, INVALID_POSTCODE_2, VALID_POSTCODE_3, INVALID_POSTCODE_3);
        assertThat(postcodeChecker.filterOnlyInvalidPostcodes(input)).containsExactly(INVALID_POSTCODE_1, INVALID_POSTCODE_2, INVALID_POSTCODE_3);
    }

}
