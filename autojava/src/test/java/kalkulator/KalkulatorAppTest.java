package kalkulator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KalkulatorAppTest {

    private final double DELTA = 0.000001;

    @Test
    public void addTwoPositiveIntegers() {
        KalkulatorApp kalkulatorek = new KalkulatorApp();
        int suma = kalkulatorek.add(1, 2);
        assertEquals(3, suma);
    }

    @Test
    public void addTwoPositiveDoubles() {
        KalkulatorApp kalkulatorek = new KalkulatorApp();
        double suma = kalkulatorek.add(1.2, 2.4);
        assertEquals(3.6, suma, DELTA);
    }

    @Test
    public void divTwoPositibeDoubles() {
        KalkulatorApp kalkulatorek = new KalkulatorApp();
        double result = kalkulatorek.div(2, 3);
        assertEquals(0.6666666, result, DELTA);
    }
}