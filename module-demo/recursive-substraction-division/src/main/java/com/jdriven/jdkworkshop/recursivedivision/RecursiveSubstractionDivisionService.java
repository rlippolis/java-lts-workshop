package com.jdriven.jdkworkshop.recursivedivision;

import com.jdriven.jdkworkshop.division.DivisionResult;
import com.jdriven.jdkworkshop.division.DivisionService;

public class RecursiveSubstractionDivisionService implements DivisionService {

    @Override
    public DivisionResult divide(int numerator, int denominator) {
        return divide(numerator, denominator, 0);
    }

    private DivisionResult divide(int numerator, int denominator, int depth) {
        if(denominator == 0) {
            throw new ArithmeticException("Division by zero");
        }

        if (numerator < denominator) {
            return new DivisionResult(depth, numerator);
        }

        return divide(numerator - denominator, denominator, depth + 1);
    }
}
