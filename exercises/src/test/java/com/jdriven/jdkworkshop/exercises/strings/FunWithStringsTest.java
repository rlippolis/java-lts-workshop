package com.jdriven.jdkworkshop.exercises.strings;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;

import org.junit.jupiter.api.Test;

public class FunWithStringsTest {

    private final FunWithStrings funWithStrings = new FunWithStrings();

    @Test
    public void testAverageLineSizeShouldBeZeroWhenStringIsEmpty() {
        final String text = "";
        final double size = funWithStrings.getAverageLineSize(text);
        assertThat(size).isEqualTo(0);
    }

    @Test
    public void testAverageLineSizeShouldBeEqualToStringSizeIfStringContainsOneLine() {
        final String text = "Ding Flof Bips";
        final double size = funWithStrings.getAverageLineSize(text);
        assertThat(size).isEqualTo(14);
    }

    @Test
    public void testAverageLineSizeShouldBeCorrectIfStringContainsMultipleLines() {
        final String text = "First line\nSecond line\nThird line";
        final double size = funWithStrings.getAverageLineSize(text);
        assertThat(size).isCloseTo(10.3333, offset(0.0001));
    }

    @Test
    public void testRepeatMeShouldRepeatTheText() {
        final String result = funWithStrings.repeatMe("na ", 16) + "BATMAN!";
        System.out.println(result);
        assertThat(result).isEqualTo("na na na na na na na na na na na na na na na na BATMAN!");
    }

}
