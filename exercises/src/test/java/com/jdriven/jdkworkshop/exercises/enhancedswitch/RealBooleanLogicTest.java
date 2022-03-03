package com.jdriven.jdkworkshop.exercises.enhancedswitch;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.UncheckedIOException;

import org.junit.jupiter.api.Test;


import com.jdriven.jdkworkshop.exercises.enhancedswitch.RealBooleanLogic.RealBoolean;

public class RealBooleanLogicTest {

    private final RealBooleanLogic realBooleanLogic = new RealBooleanLogic();

    @Test
    public void realTrueShouldReturnTrue() {
        fail("Do the exercise, then remove this line");
        assertTrue(realBooleanLogic.convertToTraditionalBoolean(RealBoolean.TRUE));
    }

    @Test
    public void realFalseShouldReturnFalse() {
        fail("Do the exercise, then remove this line");
        assertFalse(realBooleanLogic.convertToTraditionalBoolean(RealBoolean.FALSE));
    }

    @Test
    public void realFileNotFoundShouldThrowIOException() {
        fail("Do the exercise, then remove this line");
        assertThrows(UncheckedIOException.class,
                () -> realBooleanLogic.convertToTraditionalBoolean(RealBoolean.FILE_NOT_FOUND)
        );
    }

    @Test
    public void realTrueShouldBeTraditionalBooleanValue() {
        fail("Do the exercise, then remove this line");
        assertTrue(realBooleanLogic.isValidTraditionalBoolean(RealBoolean.TRUE));
    }

    @Test
    public void realFalseShouldBeTraditionalBooleanValue() {
        fail("Do the exercise, then remove this line");
        assertTrue(realBooleanLogic.isValidTraditionalBoolean(RealBoolean.FALSE));
    }

    @Test
    public void realFileNotFoundShouldNotBeTraditionalBooleanValue() {
        fail("Do the exercise, then remove this line");
        assertFalse(realBooleanLogic.isValidTraditionalBoolean(RealBoolean.FILE_NOT_FOUND));
    }
}
