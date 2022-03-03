package com.jdriven.jdkworkshop.exercises.stackwalking;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class WalkingTheStackTest {

    private final WalkingTheStack walkingTheStack = new WalkingTheStack();

    @Test
    public void testWalkingTheStackShouldCorrectlyDetermineTheCallerMethodName() {
        String methodName = walkingTheStack.whichMethodIsCallingMe();
        assertThat(methodName).isEqualTo("testWalkingTheStackShouldCorrectlyDetermineTheCallerMethodName");

        String anotherMethodName = anotherMethodCallingMe();
        assertThat(anotherMethodName).isEqualTo("anotherMethodCallingMe");
    }

    private String anotherMethodCallingMe() {
        return walkingTheStack.whichMethodIsCallingMe();
    }

    @Test
    public void testWalkingTheStackShouldCorrectlyDetermineTheCallerClass() {
        final Class<?> callingClass = walkingTheStack.whichClassIsCallingMe();
        assertThat(callingClass).isEqualTo(getClass());

        final Class<?> anotherClass = AnotherClass.anotherClassCallingMe(walkingTheStack);
        assertThat(anotherClass).isEqualTo(AnotherClass.class);
    }

    @Test
    public void testWalkingTheStackShouldCorrectlyDetermineStackSize() {
        final long callStackSize = walkingTheStack.determineApplicationStackSize();
        assertThat(callStackSize).isEqualTo(2);

        final long extraLargeStackSize = stackSizeWithExtraMethodCall();
        assertThat(extraLargeStackSize).isEqualTo(3);
    }

    private long stackSizeWithExtraMethodCall() {
        return walkingTheStack.determineApplicationStackSize();
    }

}

class AnotherClass {

    static Class<?> anotherClassCallingMe(WalkingTheStack walkingTheStack) {
        return walkingTheStack.whichClassIsCallingMe();
    }
}
