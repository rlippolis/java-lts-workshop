package com.jdriven.jdkworkshop.exercises.records;

import java.util.Objects;

/**
 * Person as a Record
 */
public record Person(String name, int age) {
    public Person {
        Objects.requireNonNull(name);
    }
}
