package com.jdriven.jdkworkshop.exercises.enhancedswitch;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.UncheckedIOException;

import org.junit.jupiter.api.Test;


import com.jdriven.jdkworkshop.exercises.enhancedswitch.RealBooleanLogic.RealBoolean;

public class RealBooleanLogicTest {

    private final RealBooleanLogic realBooleanLogic = new RealBooleanLogic();

    @Test
    public void realTrueShouldReturnTrue() {
        assertTrue(realBooleanLogic.convertToTraditionalBoolean(RealBoolean.TRUE));
    }

    @Test
    public void realFalseShouldReturnFalse() {
        assertFalse(realBooleanLogic.convertToTraditionalBoolean(RealBoolean.FALSE));
    }

    @Test
    public void realFileNotFoundShouldThrowIOException() {
        assertThrows(UncheckedIOException.class,
                () -> realBooleanLogic.convertToTraditionalBoolean(RealBoolean.FILE_NOT_FOUND)
        );
    }

    @Test
    public void realTrueShouldBeTraditionalBooleanValue() {
        assertTrue(realBooleanLogic.isValidTraditionalBoolean(RealBoolean.TRUE));
    }

    @Test
    public void realFalseShouldBeTraditionalBooleanValue() {
        assertTrue(realBooleanLogic.isValidTraditionalBoolean(RealBoolean.FALSE));
    }

    @Test
    public void realFileNotFoundShouldNotBeTraditionalBooleanValue() {
        assertFalse(realBooleanLogic.isValidTraditionalBoolean(RealBoolean.FILE_NOT_FOUND));
    }
}
