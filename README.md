# Workshop Java 9 - 17

This repository contains demos and exercises for getting acquainted with the new features
in Java 9 - 17

Prerequisites for compiling/running the code:

* JDK 17
* Gradle
* Your favorite IDE (IntelliJ)

## Exercises

The exercises are all located in the `com.jdriven.jdkworkshop.exercises` package in the `exercises` module.
Your goal is to fix the TODOs, so that the accompanying unit tests will pass
(of course, the primary goal is to understand the workings of the new features).
The exercises can be performed in any order.

## Jigsaw Demo

The demo regarding Project Jigsaw is located in a separate module, called `module-demo`.

## Demos

Some new features are so straight-forward (e.g. the anonymous diamond classes),
or need understanding of some other higher-level concept (e.g. Flow API (reactive programming) and VarHandles).
That is why we haven't created exercises for these specific parts, but the
`com.jdriven.jdkworkshop.demo` package in the `demos` module contains some demos
regarding these new features, for you to explore when desired.
