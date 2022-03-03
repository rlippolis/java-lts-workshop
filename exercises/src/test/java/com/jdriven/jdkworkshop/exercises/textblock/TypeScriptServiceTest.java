package com.jdriven.jdkworkshop.exercises.textblock;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class TypeScriptServiceTest {

    private final TypeScriptService typeScriptService = new TypeScriptService();

    @Test
    public void returnsExpectedTypeScript() throws URISyntaxException, IOException {
        String expected = Files.readString(Paths.get(getClass().getResource("expected.ts").toURI()));
        String actual = typeScriptService.getTypeScript();
        assertEquals(expected, actual);
    }

}
