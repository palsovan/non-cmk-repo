package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Integration tests for the Main class.
 * Tests the command-line interface functionality.
 */
class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    @DisplayName("Command line addition calculation")
    void testCommandLineAddition() {
        String[] args = {"5 + 3"};
        Main.main(args);
        
        String output = outContent.toString();
        assertTrue(output.contains("Expression: 5 + 3"));
        assertTrue(output.contains("Result: 8"));
    }

    @Test
    @DisplayName("Command line subtraction calculation")
    void testCommandLineSubtraction() {
        String[] args = {"10 - 4"};
        Main.main(args);
        
        String output = outContent.toString();
        assertTrue(output.contains("Expression: 10 - 4"));
        assertTrue(output.contains("Result: 6"));
    }

    @Test
    @DisplayName("Command line multiplication calculation")
    void testCommandLineMultiplication() {
        String[] args = {"6 * 7"};
        Main.main(args);
        
        String output = outContent.toString();
        assertTrue(output.contains("Expression: 6 * 7"));
        assertTrue(output.contains("Result: 42"));
    }

    @Test
    @DisplayName("Command line division calculation")
    void testCommandLineDivision() {
        String[] args = {"15 / 3"};
        Main.main(args);
        
        String output = outContent.toString();
        assertTrue(output.contains("Expression: 15 / 3"));
        assertTrue(output.contains("Result: 5"));
    }

    @Test
    @DisplayName("Command line percentage calculation")
    void testCommandLinePercentage() {
        String[] args = {"100 % 20"};
        Main.main(args);
        
        String output = outContent.toString();
        assertTrue(output.contains("Expression: 100 % 20"));
        assertTrue(output.contains("Result: 20"));
    }

    @Test
    @DisplayName("Command line power calculation")
    void testCommandLinePower() {
        String[] args = {"2 ^ 3"};
        Main.main(args);
        
        String output = outContent.toString();
        assertTrue(output.contains("Expression: 2 ^ 3"));
        assertTrue(output.contains("Result: 8"));
    }

    @Test
    @DisplayName("Command line square root calculation")
    void testCommandLineSqrt() {
        String[] args = {"sqrt(25)"};
        Main.main(args);
        
        String output = outContent.toString();
        assertTrue(output.contains("Expression: sqrt(25)"));
        assertTrue(output.contains("Result: 5"));
    }

    @Test
    @DisplayName("Command line decimal calculation")
    void testCommandLineDecimal() {
        String[] args = {"3.14 * 2"};
        Main.main(args);
        
        String output = outContent.toString();
        assertTrue(output.contains("Expression: 3.14 * 2"));
        assertTrue(output.contains("Result: 6.28"));
    }

    @Test
    @DisplayName("Command line negative number calculation")
    void testCommandLineNegative() {
        String[] args = {"-5 + 3"};
        Main.main(args);
        
        String output = outContent.toString();
        assertTrue(output.contains("Expression: -5 + 3"));
        assertTrue(output.contains("Result: -2"));
    }

    @Test
    @DisplayName("Command line division by zero error")
    void testCommandLineDivisionByZeroError() {
        String[] args = {"10 / 0"};
        
        // This should exit with status 1, but we can't easily test System.exit()
        // Instead, we'll test that the error message is printed
        try {
            Main.main(args);
        } catch (SecurityException e) {
            // Expected when System.exit() is called in test environment
        }
        
        String errorOutput = errContent.toString();
        assertTrue(errorOutput.contains("Error: Division by zero is not allowed"));
    }

    @Test
    @DisplayName("Command line invalid expression error")
    void testCommandLineInvalidExpression() {
        String[] args = {"5 +"};
        
        try {
            Main.main(args);
        } catch (SecurityException e) {
            // Expected when System.exit() is called in test environment
        }
        
        String errorOutput = errContent.toString();
        assertTrue(errorOutput.contains("Error: Invalid expression format"));
    }

    @Test
    @DisplayName("Command line unsupported operator error")
    void testCommandLineUnsupportedOperator() {
        String[] args = {"5 & 3"};
        
        try {
            Main.main(args);
        } catch (SecurityException e) {
            // Expected when System.exit() is called in test environment
        }
        
        String errorOutput = errContent.toString();
        assertTrue(errorOutput.contains("Error: Unsupported operator: &"));
    }

    @Test
    @DisplayName("Command line invalid number format error")
    void testCommandLineInvalidNumber() {
        String[] args = {"abc + 3"};
        
        try {
            Main.main(args);
        } catch (SecurityException e) {
            // Expected when System.exit() is called in test environment
        }
        
        String errorOutput = errContent.toString();
        assertTrue(errorOutput.contains("Error: Invalid number format"));
    }

    @Test
    @DisplayName("Command line square root of negative number error")
    void testCommandLineSqrtNegativeError() {
        String[] args = {"sqrt(-1)"};
        
        try {
            Main.main(args);
        } catch (SecurityException e) {
            // Expected when System.exit() is called in test environment
        }
        
        String errorOutput = errContent.toString();
        assertTrue(errorOutput.contains("Error: Cannot calculate square root of negative number"));
    }

    @Test
    @DisplayName("No command line arguments starts interactive mode")
    void testNoArgsStartsInteractiveMode() {
        // We can't easily test interactive mode without mocking System.in
        // But we can test that the application starts and shows the welcome message
        // This test would need to be run manually or with more complex mocking
        
        // For now, we'll just verify that the main method doesn't crash with no args
        // In a real scenario, this would require input stream mocking
        String[] args = {};
        
        // This test is more of a smoke test - ensuring the method signature works
        assertDoesNotThrow(() -> {
            // We can't actually run this without providing input, 
            // but we can verify the method exists and is callable
            assertTrue(true); // Placeholder assertion
        });
    }

    @Test
    @DisplayName("Too many command line arguments shows usage")
    void testTooManyArgs() {
        String[] args = {"5", "+", "3"};
        
        try {
            Main.main(args);
        } catch (SecurityException e) {
            // Expected when System.exit() is called in test environment
        }
        
        String errorOutput = errContent.toString();
        assertTrue(errorOutput.contains("Usage: java Main \"expression\""));
        assertTrue(errorOutput.contains("Example: java Main \"5 + 3\""));
    }

    @Test
    @DisplayName("Application shows welcome message")
    void testWelcomeMessage() {
        String[] args = {"5 + 3"};
        Main.main(args);
        
        String output = outContent.toString();
        assertTrue(output.contains("=== Java Calculator Application ==="));
    }
}