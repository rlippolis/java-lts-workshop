package com.jdriven.jdkworkshop.demo;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * Before Java 9, Strings were always internally stored in UTF-16 format. This means that every character
 * requires 2 bytes to be stored in memory.
 *
 * However, statistically, most Strings can be stored using only LATIN-1 encoded characters, which take up
 * only 1 byte per character. Because the JVM internally stores a lot of Strings in memory, potentially
 * a lot of memory can be saved by using 1 byte per character instead of 2, where possible.
 *
 * Since Java 9, the JVM will use 'compact' Strings where possible. When creating a String, the JVM
 * checks whether or not every character in the String can be represented in LATIN-1, and if so,
 * stores the String in 'compact' format (1 byte per character). Otherwise, the original UTF-16 format
 * is used (2 bytes per character). Because Strings are immutable, the format used for storage doesn't have
 * to change after String creation.
 *
 * In the class below we demonstrate the effect. Read the code in the unit test and run it, check the console output,
 * trying to understand what is shown.
 */
public class CompactStringsTest {

    @Test
    public void compactStrings() throws Exception {
        // This String contains a non LATIN-1 character (a smiley), so UTF-16 format is used -> 2 bytes per character
        String notCompact = "This is a String 😎";
        // This String contains only ASCII characters, so LATIN-1 format is used -> 1 byte per character
        String compact = "This is a String";

        // We get the internal byte array representation of the Strings using a dirty hack (not recommended for production usage)
        byte[] notCompactStringInBytes = getByteRepresentation(notCompact);
        byte[] compactStringInBytes = getByteRepresentation(compact);

        // Print the results, compare the length of the byte arrays
        System.out.printf("Not compact:%n%s%n%n", Arrays.toString(notCompactStringInBytes));
        System.out.printf("Compact:%n%s%n%n", Arrays.toString(compactStringInBytes));

        int notCompactLength = notCompactStringInBytes.length;
        int compactLength = compactStringInBytes.length;
        int difference = notCompactLength - compactLength;
        System.out.printf("Size difference: %d - %d = %d bytes%n", notCompactLength, compactLength, difference);
    }

    private byte[] getByteRepresentation(String str) throws NoSuchFieldException, IllegalAccessException {
        Field valueField = String.class.getDeclaredField("value");
        valueField.setAccessible(true);
        return (byte[]) valueField.get(str);
    }
}
