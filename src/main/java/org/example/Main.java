package org.example;

import java.util.Scanner;

/**
 * Main class for the Calculator application.
 * Provides both command-line argument processing and interactive mode.
 */
public class Main {
    private static final Calculator calculator = new Calculator();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Java Calculator Application ===");
        
        // If command line arguments are provided, process them
        if (args.length > 0) {
            processCommandLineArgs(args);
        } else {
            // Otherwise, start interactive mode
            runInteractiveMode();
        }
    }

    /**
     * Processes command line arguments for single calculation.
     * Expected format: java Main "5 + 3" or java Main "sqrt(9)"
     */
    private static void processCommandLineArgs(String[] args) {
        if (args.length == 1) {
            try {
                double result = calculator.evaluate(args[0]);
                System.out.println("Expression: " + args[0]);
                System.out.println("Result: " + formatResult(result));
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                System.exit(1);
            }
        } else {
            System.err.println("Usage: java Main \"expression\"");
            System.err.println("Example: java Main \"5 + 3\"");
            System.exit(1);
        }
    }

    /**
     * Runs the interactive calculator mode.
     */
    private static void runInteractiveMode() {
        System.out.println("\nWelcome to the Interactive Calculator!");
        System.out.println("Supported operations:");
        System.out.println("  Addition: 5 + 3");
        System.out.println("  Subtraction: 10 - 4");
        System.out.println("  Multiplication: 6 * 7");
        System.out.println("  Division: 15 / 3");
        System.out.println("  Percentage: 100 % 20 (20% of 100)");
        System.out.println("  Power: 2 ^ 3 (2 to the power of 3)");
        System.out.println("  Square root: sqrt(25)");
        System.out.println("\nType 'help' for this message, 'quit' or 'exit' to quit.\n");

        while (true) {
            System.out.print("Calculator> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            // Handle special commands
            if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("exit")) {
                System.out.println("Thank you for using the Calculator. Goodbye!");
                break;
            }

            if (input.equalsIgnoreCase("help")) {
                showHelp();
                continue;
            }

            if (input.equalsIgnoreCase("clear")) {
                clearScreen();
                continue;
            }

            // Process calculation
            try {
                double result = calculator.evaluate(input);
                System.out.println("Result: " + formatResult(result));
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                System.out.println("Type 'help' for usage information.");
            }
            
            System.out.println(); // Add blank line for readability
        }
        
        scanner.close();
    }

    /**
     * Shows detailed help information.
     */
    private static void showHelp() {
        System.out.println("\n=== Calculator Help ===");
        System.out.println("Supported Operations:");
        System.out.println("  Addition:       5 + 3");
        System.out.println("  Subtraction:    10 - 4");
        System.out.println("  Multiplication: 6 * 7");
        System.out.println("  Division:       15 / 3");
        System.out.println("  Percentage:     100 % 20  (calculates 20% of 100)");
        System.out.println("  Power:          2 ^ 3     (2 to the power of 3)");
        System.out.println("  Square root:    sqrt(25)");
        System.out.println();
        System.out.println("Special Commands:");
        System.out.println("  help  - Show this help message");
        System.out.println("  clear - Clear the screen");
        System.out.println("  quit  - Exit the calculator");
        System.out.println("  exit  - Exit the calculator");
        System.out.println();
        System.out.println("Notes:");
        System.out.println("  - Use spaces around operators: '5 + 3' not '5+3'");
        System.out.println("  - Decimal numbers are supported: '3.14 * 2'");
        System.out.println("  - Negative numbers are supported: '-5 + 3'");
        System.out.println("  - Division by zero will show an error");
        System.out.println();
    }

    /**
     * Clears the screen (works on most terminals).
     */
    private static void clearScreen() {
        try {
            // Try to clear screen for different operating systems
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[2J\033[H");
                System.out.flush();
            }
        } catch (Exception e) {
            // If clearing fails, just print some newlines
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
        System.out.println("=== Java Calculator Application ===");
    }

    /**
     * Formats the result for display, removing unnecessary decimal places.
     */
    private static String formatResult(double result) {
        // If the result is a whole number, display it without decimal places
        if (result == Math.floor(result) && !Double.isInfinite(result)) {
            return String.format("%.0f", result);
        } else {
            // Otherwise, display with up to 6 decimal places, removing trailing zeros
            return String.format("%.6f", result).replaceAll("0*$", "").replaceAll("\\.$", "");
        }
    }
}
