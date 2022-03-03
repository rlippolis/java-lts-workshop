package com.jdriven.jdkworkshop.exercises.enhancedswitch;

import java.io.FileNotFoundException;
import java.io.UncheckedIOException;

public class RealBooleanLogic {

    /**
     * This method currently uses a 'traditional' switch statement. Rewrite this to a Java 12 'enhanced' switch.
     */
    public boolean convertToTraditionalBoolean(RealBoolean realBoolean) {
        boolean result;
        switch (realBoolean) {
            case TRUE:
                result = true;
                break;
            case FALSE:
                result = false;
                break;
            case FILE_NOT_FOUND:
                throw new UncheckedIOException(new FileNotFoundException());
            default:
                throw new IllegalArgumentException("Seriously?!");
        }

        System.out.println("Result: " + result);
        return result;
    }

    public boolean isValidTraditionalBoolean(RealBoolean realBoolean) {
        switch (realBoolean) {
            case TRUE:
            case FALSE:
                return true;
            default:
                return false;
        }
    }

    // See: https://thedailywtf.com/articles/What_Is_Truth_0x3f_
    enum RealBoolean {
        TRUE,
        FALSE,
        FILE_NOT_FOUND
    }
}
