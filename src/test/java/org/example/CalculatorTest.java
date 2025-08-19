package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Calculator class.
 * Tests all arithmetic operations and edge cases.
 */
class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("Addition of positive numbers")
    void testAddPositiveNumbers() {
        assertEquals(5.0, calculator.add(2.0, 3.0), 0.001);
        assertEquals(10.5, calculator.add(4.2, 6.3), 0.001);
    }

    @Test
    @DisplayName("Addition with negative numbers")
    void testAddWithNegativeNumbers() {
        assertEquals(-1.0, calculator.add(-3.0, 2.0), 0.001);
        assertEquals(-5.0, calculator.add(-2.0, -3.0), 0.001);
        assertEquals(0.0, calculator.add(-5.0, 5.0), 0.001);
    }

    @Test
    @DisplayName("Addition with zero")
    void testAddWithZero() {
        assertEquals(5.0, calculator.add(5.0, 0.0), 0.001);
        assertEquals(0.0, calculator.add(0.0, 0.0), 0.001);
    }

    @Test
    @DisplayName("Subtraction of positive numbers")
    void testSubtractPositiveNumbers() {
        assertEquals(2.0, calculator.subtract(5.0, 3.0), 0.001);
        assertEquals(-1.0, calculator.subtract(3.0, 4.0), 0.001);
    }

    @Test
    @DisplayName("Subtraction with negative numbers")
    void testSubtractWithNegativeNumbers() {
        assertEquals(-5.0, calculator.subtract(-3.0, 2.0), 0.001);
        assertEquals(1.0, calculator.subtract(-2.0, -3.0), 0.001);
    }

    @Test
    @DisplayName("Subtraction with zero")
    void testSubtractWithZero() {
        assertEquals(5.0, calculator.subtract(5.0, 0.0), 0.001);
        assertEquals(-5.0, calculator.subtract(0.0, 5.0), 0.001);
    }

    @Test
    @DisplayName("Multiplication of positive numbers")
    void testMultiplyPositiveNumbers() {
        assertEquals(15.0, calculator.multiply(3.0, 5.0), 0.001);
        assertEquals(26.46, calculator.multiply(4.2, 6.3), 0.001);
    }

    @Test
    @DisplayName("Multiplication with negative numbers")
    void testMultiplyWithNegativeNumbers() {
        assertEquals(-15.0, calculator.multiply(-3.0, 5.0), 0.001);
        assertEquals(15.0, calculator.multiply(-3.0, -5.0), 0.001);
    }

    @Test
    @DisplayName("Multiplication with zero")
    void testMultiplyWithZero() {
        assertEquals(0.0, calculator.multiply(5.0, 0.0), 0.001);
        assertEquals(0.0, calculator.multiply(0.0, 5.0), 0.001);
    }

    @Test
    @DisplayName("Division of positive numbers")
    void testDividePositiveNumbers() {
        assertEquals(2.0, calculator.divide(10.0, 5.0), 0.001);
        assertEquals(2.5, calculator.divide(5.0, 2.0), 0.001);
    }

    @Test
    @DisplayName("Division with negative numbers")
    void testDivideWithNegativeNumbers() {
        assertEquals(-2.0, calculator.divide(-10.0, 5.0), 0.001);
        assertEquals(2.0, calculator.divide(-10.0, -5.0), 0.001);
    }

    @Test
    @DisplayName("Division by zero throws exception")
    void testDivideByZero() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, 
            () -> calculator.divide(10.0, 0.0));
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }

    @Test
    @DisplayName("Division of zero by number")
    void testDivideZeroByNumber() {
        assertEquals(0.0, calculator.divide(0.0, 5.0), 0.001);
    }

    @Test
    @DisplayName("Percentage calculation")
    void testPercentage() {
        assertEquals(20.0, calculator.percentage(100.0, 20.0), 0.001);
        assertEquals(15.0, calculator.percentage(50.0, 30.0), 0.001);
        assertEquals(0.0, calculator.percentage(100.0, 0.0), 0.001);
    }

    @Test
    @DisplayName("Power calculation")
    void testPower() {
        assertEquals(8.0, calculator.power(2.0, 3.0), 0.001);
        assertEquals(1.0, calculator.power(5.0, 0.0), 0.001);
        assertEquals(0.25, calculator.power(2.0, -2.0), 0.001);
    }

    @Test
    @DisplayName("Square root calculation")
    void testSqrt() {
        assertEquals(3.0, calculator.sqrt(9.0), 0.001);
        assertEquals(5.0, calculator.sqrt(25.0), 0.001);
        assertEquals(0.0, calculator.sqrt(0.0), 0.001);
        assertEquals(2.236, calculator.sqrt(5.0), 0.001);
    }

    @Test
    @DisplayName("Square root of negative number throws exception")
    void testSqrtNegativeNumber() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, 
            () -> calculator.sqrt(-1.0));
        assertEquals("Cannot calculate square root of negative number", exception.getMessage());
    }

    @Test
    @DisplayName("Evaluate simple addition expression")
    void testEvaluateAddition() {
        assertEquals(8.0, calculator.evaluate("5 + 3"), 0.001);
        assertEquals(7.5, calculator.evaluate("2.5 + 5.0"), 0.001);
    }

    @Test
    @DisplayName("Evaluate simple subtraction expression")
    void testEvaluateSubtraction() {
        assertEquals(2.0, calculator.evaluate("5 - 3"), 0.001);
        assertEquals(-2.5, calculator.evaluate("2.5 - 5.0"), 0.001);
    }

    @Test
    @DisplayName("Evaluate simple multiplication expression")
    void testEvaluateMultiplication() {
        assertEquals(15.0, calculator.evaluate("5 * 3"), 0.001);
        assertEquals(12.5, calculator.evaluate("2.5 * 5.0"), 0.001);
    }

    @Test
    @DisplayName("Evaluate simple division expression")
    void testEvaluateDivision() {
        assertEquals(2.0, calculator.evaluate("6 / 3"), 0.001);
        assertEquals(0.5, calculator.evaluate("2.5 / 5.0"), 0.001);
    }

    @Test
    @DisplayName("Evaluate percentage expression")
    void testEvaluatePercentage() {
        assertEquals(20.0, calculator.evaluate("100 % 20"), 0.001);
        assertEquals(15.0, calculator.evaluate("50 % 30"), 0.001);
    }

    @Test
    @DisplayName("Evaluate power expression")
    void testEvaluatePower() {
        assertEquals(8.0, calculator.evaluate("2 ^ 3"), 0.001);
        assertEquals(25.0, calculator.evaluate("5 ^ 2"), 0.001);
    }

    @Test
    @DisplayName("Evaluate square root expression")
    void testEvaluateSqrt() {
        assertEquals(3.0, calculator.evaluate("sqrt(9)"), 0.001);
        assertEquals(5.0, calculator.evaluate("sqrt(25)"), 0.001);
        assertEquals(0.0, calculator.evaluate("sqrt(0)"), 0.001);
    }

    @Test
    @DisplayName("Evaluate expression with division by zero throws exception")
    void testEvaluateDivisionByZero() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, 
            () -> calculator.evaluate("10 / 0"));
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }

    @Test
    @DisplayName("Evaluate expression with negative square root throws exception")
    void testEvaluateNegativeSqrt() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, 
            () -> calculator.evaluate("sqrt(-1)"));
        assertEquals("Cannot calculate square root of negative number", exception.getMessage());
    }

    @Test
    @DisplayName("Evaluate null expression throws exception")
    void testEvaluateNullExpression() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> calculator.evaluate(null));
        assertEquals("Expression cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Evaluate empty expression throws exception")
    void testEvaluateEmptyExpression() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> calculator.evaluate(""));
        assertEquals("Expression cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Evaluate invalid expression format throws exception")
    void testEvaluateInvalidFormat() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> calculator.evaluate("5 +"));
        assertEquals("Invalid expression format. Expected: 'number operator number'", exception.getMessage());
    }

    @Test
    @DisplayName("Evaluate unsupported operator throws exception")
    void testEvaluateUnsupportedOperator() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> calculator.evaluate("5 & 3"));
        assertEquals("Unsupported operator: &", exception.getMessage());
    }

    @Test
    @DisplayName("Evaluate invalid number format throws exception")
    void testEvaluateInvalidNumber() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> calculator.evaluate("abc + 3"));
        assertEquals("Invalid number format in expression: abc + 3", exception.getMessage());
    }

    @Test
    @DisplayName("Evaluate invalid sqrt format throws exception")
    void testEvaluateInvalidSqrtFormat() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> calculator.evaluate("sqrt(abc)"));
        assertEquals("Invalid number in sqrt expression: abc", exception.getMessage());
    }
}