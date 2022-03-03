package com.jdriven.jdkworkshop.demo;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

import org.junit.jupiter.api.Test;

@SuppressWarnings("FieldCanBeLocal")
public class VarHandleTest {

    private volatile int x;                      // Default: volatile
    private static final VarHandle X;            // Naming: uppercase

    static {
        try {
            X = MethodHandles.lookup().findVarHandle(VarHandleTest.class, "x", int.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new Error(e);
        }
    }

    @Test
    public void varHandleTest() {
        // Plain get/set: non-volatile
        X.set(this, 1);
        System.out.println(X.get(this));

        // Opaque get/set: program order, no memory order guarantees
        X.setOpaque(this, 2);
        System.out.println(X.getOpaque(this));

        // Release set: prior loads and stores not reordered
        X.setRelease(this, 3);
        System.out.println(X.get(this));

        // Compare and set: only set value (with volatile semantics) if it matches the expected value
        X.compareAndSet(this, 3, 4);
        System.out.println(X.get(this));

        // Volatile set/get: behavior equal to volatile declaration
        X.setVolatile(this, 5);
        System.out.println(X.getVolatile(this));

        x = 6;
        System.out.println(x);
    }

}
