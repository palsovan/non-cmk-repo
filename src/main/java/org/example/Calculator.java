package org.example;

/**
 * A simple calculator class that provides basic arithmetic operations.
 * This class supports addition, subtraction, multiplication, and division
 * with proper error handling for edge cases like division by zero.
 */
public class Calculator {

    /**
     * Adds two numbers.
     *
     * @param a the first number
     * @param b the second number
     * @return the sum of a and b
     */
    public double add(double a, double b) {
        return a + b;
    }

    /**
     * Subtracts the second number from the first number.
     *
     * @param a the first number (minuend)
     * @param b the second number (subtrahend)
     * @return the difference of a and b
     */
    public double subtract(double a, double b) {
        return a - b;
    }

    /**
     * Multiplies two numbers.
     *
     * @param a the first number
     * @param b the second number
     * @return the product of a and b
     */
    public double multiply(double a, double b) {
        return a * b;
    }

    /**
     * Divides the first number by the second number.
     *
     * @param a the dividend
     * @param b the divisor
     * @return the quotient of a divided by b
     * @throws ArithmeticException if b is zero
     */
    public double divide(double a, double b) {
        if (b == 0.0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return a / b;
    }

    /**
     * Calculates the percentage of a number.
     *
     * @param number the base number
     * @param percentage the percentage to calculate
     * @return the percentage of the number
     */
    public double percentage(double number, double percentage) {
        return (number * percentage) / 100.0;
    }

    /**
     * Calculates the power of a number.
     *
     * @param base the base number
     * @param exponent the exponent
     * @return base raised to the power of exponent
     */
    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    /**
     * Calculates the square root of a number.
     *
     * @param number the number to find the square root of
     * @return the square root of the number
     * @throws ArithmeticException if the number is negative
     */
    public double sqrt(double number) {
        if (number < 0) {
            throw new ArithmeticException("Cannot calculate square root of negative number");
        }
        return Math.sqrt(number);
    }

    /**
     * Evaluates a simple mathematical expression in the format "number operator number".
     * Supported operators: +, -, *, /, %, ^, sqrt
     *
     * @param expression the mathematical expression to evaluate
     * @return the result of the expression
     * @throws IllegalArgumentException if the expression is invalid
     * @throws ArithmeticException if a mathematical error occurs (e.g., division by zero)
     */
    public double evaluate(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be null or empty");
        }

        expression = expression.trim();

        // Handle square root specially
        if (expression.toLowerCase().startsWith("sqrt(") && expression.endsWith(")")) {
            String numberStr = expression.substring(5, expression.length() - 1).trim();
            try {
                double number = Double.parseDouble(numberStr);
                return sqrt(number);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number in sqrt expression: " + numberStr);
            }
        }

        // Split the expression into parts
        String[] parts = expression.split("\\s+");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid expression format. Expected: 'number operator number'");
        }

        try {
            double a = Double.parseDouble(parts[0]);
            String operator = parts[1];
            double b = Double.parseDouble(parts[2]);

            switch (operator) {
                case "+":
                    return add(a, b);
                case "-":
                    return subtract(a, b);
                case "*":
                    return multiply(a, b);
                case "/":
                    return divide(a, b);
                case "%":
                    return percentage(a, b);
                case "^":
                    return power(a, b);
                default:
                    throw new IllegalArgumentException("Unsupported operator: " + operator);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format in expression: " + expression);
        }
    }
}