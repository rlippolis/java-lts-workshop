package com.jdriven.jdkworkshop.demo;

import org.junit.jupiter.api.Test;

public class UnderscoreIdentifierTest {

    @Test
    public void test() {

        String _variable = "is allowed";
        String variable_ = "is allowed";
    }

    public String _method() {
        return "is allowed";
    }
}
