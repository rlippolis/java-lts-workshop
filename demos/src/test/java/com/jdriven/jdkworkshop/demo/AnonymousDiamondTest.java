package com.jdriven.jdkworkshop.demo;

import org.junit.jupiter.api.Test;

public class AnonymousDiamondTest {
    public static abstract class SomeGenericClass<T> {
        private final T value;

        protected SomeGenericClass(T value) {
            this.value = value;
        }

        abstract void doSomething();

        public T getValue() {
            return value;
        }
    }

    @Test
    public void test() {
        new SomeGenericClass<>(10) {
            @Override
            void doSomething() {
                System.out.println(getValue());
            }
        }.doSomething();
    }
}
