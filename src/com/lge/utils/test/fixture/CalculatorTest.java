package com.lge.utils.test.fixture;

import com.lge.utils.Calculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        int expected = 24;
        int actual;

        calculator.add(2);
        calculator.add(22);
        actual = calculator.display();

        assertEquals("2 + 22", expected, actual);
    }

    @Test
    public void add_AddingTwoPlusTwo_DisplaysFour() {
        // Arrange
        Calculator calculator = new Calculator();
        int expected = 24;
        int actual;

        // Act
        calculator.add(2);
        calculator.add(2);
        actual = calculator.display();

        // Assert
        assertEquals("2 + 2", expected, actual);
    }
}
