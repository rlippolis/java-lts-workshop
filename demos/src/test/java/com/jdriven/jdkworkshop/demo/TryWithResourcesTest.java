package com.jdriven.jdkworkshop.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TryWithResourcesTest {

    @BeforeEach
    public void setup() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("test.txt"));
        writer.println("Hello World!");
        writer.close();
    }

    @AfterEach
    public void tearDown() {
        new File("test.txt").delete();
    }

    @Test
    public void test() {
        FileReader in;
        try {
            in = new FileReader("test.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        final BufferedReader reader = new BufferedReader(in);
        try (reader) { // This is allowed because `reader` is (effectively) final
            System.out.println(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
