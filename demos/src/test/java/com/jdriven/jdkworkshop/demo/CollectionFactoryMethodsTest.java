package com.jdriven.jdkworkshop.demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class CollectionFactoryMethodsTest {

    @Test
    public void java8() {
        Set<String> alphabet = new HashSet<>();
        alphabet.add("A");
        alphabet.add("B");
        alphabet.add("C");
        alphabet = Collections.unmodifiableSet(alphabet);


        alphabet = new HashSet<>(Arrays.asList("A", "B", "C"));
        alphabet = Collections.unmodifiableSet(alphabet);


        alphabet = Stream.of("A", "B", "C").collect(Collectors.toSet());
        alphabet = Collections.unmodifiableSet(alphabet);


        Map<String, String> caps = new HashMap<>();
        caps.put("a", "A");
        caps.put("b", "B");
        caps.put("C", "C");
        caps = Collections.unmodifiableMap(caps);
    }

    @Test
    public void java9() {
        Set<String> alphabet = Set.of("A", "B", "C");

        alphabet.forEach(System.out::println);

        Map<String, String> caps = Map.of("a", "A", "b", "B", "c", "C", "d", "D");
        caps.forEach((k, v) -> System.out.println(k + " -> " + v));

        caps = Map.ofEntries(
                Map.entry("a", "A"),
                Map.entry("b", "B"),
                Map.entry("c", "C"),
                Map.entry("d", "D"),
                Map.entry("e", "E"),
                Map.entry("f", "F"),
                Map.entry("g", "G"),
                Map.entry("h", "H"));

        caps.forEach((k, v) -> System.out.println(k + " -> " + v));
    }

}
