package com.jdriven.jdkworkshop.exercises.records;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PersonTest {

    @Test
    public void testTwoSimilarPersonsShouldBeEqual() {
        var name = "Gekke Gerrit";
        var age = 42;

        var person1 = new Person(name, age);
        var person2 = new Person(name, age);

        assertAll(
                () -> assertEquals(person1.hashCode(), person2.hashCode(), "hashCode should be the same"),
                () -> assertEquals(person1, person2, "persons should be equal"),
                () -> assertTrue(person1.toString().contains(name), "person String should contain the name"),
                () -> assertTrue(person1.toString().contains(String.valueOf(age)), "person String should contain the age")
        );
    }

    @Test
    public void testThatOnlyNonNullNamesAreAccepted() {
        assertThrows(NullPointerException.class,
                () -> new Person(null, 42)
        );
    }
}
