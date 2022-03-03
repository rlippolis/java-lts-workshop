package com.jdriven.jdkworkshop.demo;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;

import org.junit.jupiter.api.Test;

public class SerializationTest {
    private final byte[] data;

    private static class Dog implements Serializable {
        @Override
        public String toString() {
            return "Dog";
        }
    }

    private static class Cat implements Serializable {
        @Override
        public String toString() {
            return "Cat";
        }
    }

    private static class Goldfish implements Serializable {
        @Override
        public String toString() {
            return "Goldfish";
        }
    }

    public SerializationTest() throws IOException {
        try (ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bytesOut)) {
            out.writeObject(new Dog());
            out.writeObject(new Cat());
            out.writeObject(new Goldfish());

            data = bytesOut.toByteArray();
        }
    }

    @Test
    public void test() throws IOException, ClassNotFoundException {
        assertThrows(OptionalDataException.class, () -> {
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(data));
            in.setObjectInputFilter(filterInfo -> Dog.class.isAssignableFrom(filterInfo.serialClass()) ?
                    ObjectInputFilter.Status.ALLOWED : ObjectInputFilter.Status.REJECTED);

            for(;;) {
                try {
                    System.out.println(in.readObject());
                } catch (InvalidClassException e) {
                    System.out.println(e.getMessage());
                } catch (EOFException e) {
                    break;
                }
            }
        });
    }
}
