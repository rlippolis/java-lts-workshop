package com.jdriven.jdkworkshop.exercises.stackwalking;

import java.lang.StackWalker.Option;
import java.lang.StackWalker.StackFrame;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Java uses the concept of a call stack to keep track of the flow of methods called in a specific thread.
 * So if method A calls method B, which in turn calls method C, and method C is currently being executed, the call stack
 * would be something like this:
 *
 * - C
 * - B
 * - A
 *
 * After C is finished, it is removed from the stack and B is now at the top. You will probably recognise this concept
 * from when an Exception is thrown. The Exception will show a pretty stack trace in your application log... :)
 *
 * Before Java 9, when one would like to gather information about the current call stack, they would create
 * a new Exception, and use the {@link Exception#getStackTrace()} method to retrieve the needed information.
 *
 * This is a rather expensive operation, because the method would first need to gather information about the
 * entire call stack, constructing an array of StackTraceElements. Keep in mind that a call stack can get pretty large
 * in an enterprise application.
 *
 * In Java 9, the concept of {@link StackWalker} is introduced. This is a class which helps you navigate the current
 * call stack using the Stream API. This means you have the possibility of navigating the call stack in a lazy way,
 * only initializing the parts of the call stack which are of interest, leading to a better performance.
 */
public class WalkingTheStack {

    /**
     * Use a {@link StackWalker} instance to return the name of the method which is calling this method.
     *
     * To obtain an instance of a StackWalker, use the {@link StackWalker#getInstance()} factory method.
     * Then, use the {@link StackWalker#walk(Function)} method. This method needs a lambda function as argument.
     * The lambda receives an instance of {@link Stream<StackFrame>}, and should return
     * the name of the calling method as a {@link String}.
     */
    public String whichMethodIsCallingMe() {
        return StackWalker.getInstance().walk(
                stackFrameStream -> stackFrameStream
                        .skip(1)
                        .findFirst()
                        .map(StackFrame::getMethodName)
                        .orElse(null));
    }

    /**
     * Use a {@link StackWalker} instance to return the class object containing the method which is calling this method.
     *
     * NOTE: If you would like to extract information about classes while using the StackWalker, it is required to
     * pass an additional option to the StackWalker factory method:
     * <code>
     *     StackWalker.getInstance(Option.RETAIN_CLASS_REFERENCE);
     * </code>
     * Otherwise an UnsupportedOperationException will be thrown.
     */
    public Class<?> whichClassIsCallingMe() {
        return StackWalker.getInstance(Option.RETAIN_CLASS_REFERENCE).getCallerClass();
    }

    /**
     * Use a {@link StackWalker} instance to return the number of elements on the call stack which belong to this project.
     * (all stack elements which have a package name starting with <code>com.jdriven.jdkworkshop</code>).
     */
    public long determineApplicationStackSize() {
        return StackWalker.getInstance().walk(
                stackFrameStream -> stackFrameStream
                        .filter(stackFrame -> stackFrame.getClassName().startsWith("com.jdriven.jdkworkshop"))
                        .count()
        );
    }
}
