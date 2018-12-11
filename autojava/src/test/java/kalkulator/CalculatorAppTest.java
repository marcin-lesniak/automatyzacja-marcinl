package kalkulator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorAppTest {

    private final double DELTA = 0.000001;

    @Test
    public void addTwoPositiveIntegers() {
        CalculatorApp calculator = new CalculatorApp();
        int suma = calculator.add(1, 2);
        assertEquals(3, suma);
    }

    @Test
    public void addTwoPositiveDoubles() {
        CalculatorApp calculator = new CalculatorApp();
        double suma = calculator.add(1.2, 2.4);
        assertEquals(3.6, suma, DELTA);
    }

    @Test
    public void divTwoPositibeDoubles() {
        CalculatorApp calculator = new CalculatorApp();
        double result = calculator.div(2, 3);
        assertEquals(0.6666666, result, DELTA);
    }
}