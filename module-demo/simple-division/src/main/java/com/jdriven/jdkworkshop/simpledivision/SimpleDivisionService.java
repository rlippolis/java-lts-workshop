package com.jdriven.jdkworkshop.simpledivision;

import com.jdriven.jdkworkshop.division.DivisionResult;
import com.jdriven.jdkworkshop.division.DivisionService;

public class SimpleDivisionService implements DivisionService {
    @Override
    public DivisionResult divide(int numerator, int denominator) {
        return new DivisionResult(numerator / denominator, numerator % denominator);
    }
}
