package com.jdriven.jdkworkshop.recursivedivision;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


import com.jdriven.jdkworkshop.division.DivisionResult;
import com.jdriven.jdkworkshop.division.DivisionService;

public class RecursiveSubstractionDivisionServiceTest {
    private final DivisionService service = new RecursiveSubstractionDivisionService();

    @Test
    public void testDoesNotAllowDivideByZero() {
        assertThrows(ArithmeticException.class,
                () -> service.divide(1, 0)
        );
    }

    @Test
    public void testDividingZeroReturnsZero() {
        assertEquals(new DivisionResult(0, 0), service.divide(0, 1));
        assertEquals(new DivisionResult(0, 0), service.divide(0, 2));
    }

    @Test
    public void testWholeNumberQuotient() {
        assertEquals(new DivisionResult(3, 0), service.divide(15, 5));
        assertEquals(new DivisionResult(1, 0), service.divide(1, 1));
        assertEquals(new DivisionResult(7, 0), service.divide(14, 2));
        assertEquals(new DivisionResult(5, 0), service.divide(5, 1));
    }

    @Test
    public void testRemainderQuotient() {
        assertEquals(new DivisionResult(3, 1), service.divide(16, 5));
        assertEquals(new DivisionResult(3, 3), service.divide(18, 5));
        assertEquals(new DivisionResult(3, 3), service.divide(15, 4));
        assertEquals(new DivisionResult(3, 2), service.divide(14, 4));
    }
}
