package com.jdriven.jdkworkshop.client;

import java.util.ServiceLoader;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.jdriven.jdkworkshop.division.DivisionResult;
import com.jdriven.jdkworkshop.division.DivisionService;

public class ClientTest {

    private Client client;

    @BeforeEach
    public void setup() {

        DivisionService service = ServiceLoader.load(DivisionService.class)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No DivisionService implementation available"));

        client = new Client(service);
    }

    @Test
    public void test() {
        int numerator = 19;
        int denominator = 7;

        DivisionResult expectedResult = new DivisionResult(2, 5);

        Assertions.assertThat(client.divide(numerator, denominator)).isEqualTo(expectedResult);

    }
}
