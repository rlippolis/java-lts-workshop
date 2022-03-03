package com.jdriven.jdkworkshop.client;

import com.jdriven.jdkworkshop.division.DivisionResult;
import com.jdriven.jdkworkshop.division.DivisionService;

public class Client {
    private final DivisionService service;

    public Client(DivisionService service) {
        this.service = service;
    }

    public DivisionResult divide(int numerator, int denominator) {
        return service.divide(numerator, denominator);
    }
}
