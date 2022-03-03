package com.jdriven.jdkworkshop.exercises.var;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Since Java 10, local variable type inference is supported. This means that for local variables, the Java compiler
 * can automatically infer the type of the variable. We no longer need to specify it, but instead we use
 * the newly introduced 'var' keyword:
 * <code>
 *     var count = 5; // Automatically inferred to int by the compiler
 * </code>
 *
 * In this class, try to find all the places where the type parameter can be omitted / replaced by the 'var' keyword,
 * without causing a compilation (or runtime) failure.
 */
@SuppressWarnings("Convert2MethodRef")
public class LocalVariableTypeInference {

    public static void main(String... args) {
        String helloText = "Hello";
        String worldText = "world!";

        System.out.println(helloText + " " + worldText);

        LocalVariableTypeInference localVariableTypeInference = new LocalVariableTypeInference();

        System.out.println("This class is called: " + localVariableTypeInference.getClass().getSimpleName());

        Stream<Integer> evenNumbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .filter((Integer x) -> x % 2 == 0);

        System.out.println("Even numbers: " + evenNumbers.map(String::valueOf).collect(Collectors.joining(", ", "[", "]")));

        long totalWordSize = Stream.of("aap", null, "noot", null, "mies")
                .filter(Objects::nonNull)
                .mapToInt((String s) -> s.length())
                .sum();

        System.out.println("Total word size: " + totalWordSize);
    }
}
