# Java Calculator Application

A comprehensive command-line calculator application built in Java with Maven. This calculator supports basic arithmetic operations, advanced functions, and provides both interactive and command-line modes.

## Features

### Supported Operations
- **Addition**: `5 + 3`
- **Subtraction**: `10 - 4`
- **Multiplication**: `6 * 7`
- **Division**: `15 / 3`
- **Percentage**: `100 % 20` (calculates 20% of 100)
- **Power**: `2 ^ 3` (2 to the power of 3)
- **Square Root**: `sqrt(25)`

### Key Features
- ✅ Interactive command-line interface
- ✅ Single calculation mode via command-line arguments
- ✅ Comprehensive error handling (division by zero, invalid input, etc.)
- ✅ Support for decimal numbers and negative numbers
- ✅ Built-in help system
- ✅ Cross-platform screen clearing
- ✅ Extensive unit and integration tests
- ✅ Clean, formatted output

## Requirements

- Java 8 or higher
- Maven 3.6 or higher

## Building the Application

```bash
# Clone the repository
git clone <repository-url>
cd <repository-directory>

# Compile the application
mvn compile

# Run tests
mvn test

# Package the application
mvn package
```

## Running the Application

### Interactive Mode

Start the calculator in interactive mode:

```bash
# Using Maven
mvn exec:java -Dexec.mainClass="org.example.Main"

# Or using compiled classes
java -cp target/classes org.example.Main
```

In interactive mode, you can:
- Enter mathematical expressions
- Use special commands: `help`, `clear`, `quit`, `exit`
- Get immediate feedback and error messages

Example interactive session:
```
=== Java Calculator Application ===

Welcome to the Interactive Calculator!
Supported operations:
  Addition: 5 + 3
  Subtraction: 10 - 4
  Multiplication: 6 * 7
  Division: 15 / 3
  Percentage: 100 % 20 (20% of 100)
  Power: 2 ^ 3 (2 to the power of 3)
  Square root: sqrt(25)

Type 'help' for this message, 'quit' or 'exit' to quit.

Calculator> 5 + 3
Result: 8

Calculator> sqrt(25)
Result: 5

Calculator> 100 % 15
Result: 15

Calculator> quit
Thank you for using the Calculator. Goodbye!
```

### Command-Line Mode

Perform a single calculation:

```bash
# Using Maven
mvn exec:java -Dexec.mainClass="org.example.Main" -Dexec.args="\"5 + 3\""

# Or using compiled classes
java -cp target/classes org.example.Main "5 + 3"
java -cp target/classes org.example.Main "sqrt(16)"
java -cp target/classes org.example.Main "2 ^ 10"
```

## Usage Examples

### Basic Arithmetic
```bash
java -cp target/classes org.example.Main "10 + 5"     # Result: 15
java -cp target/classes org.example.Main "20 - 8"     # Result: 12
java -cp target/classes org.example.Main "6 * 7"      # Result: 42
java -cp target/classes org.example.Main "15 / 3"     # Result: 5
```

### Advanced Operations
```bash
java -cp target/classes org.example.Main "100 % 25"   # Result: 25 (25% of 100)
java -cp target/classes org.example.Main "2 ^ 8"      # Result: 256
java -cp target/classes org.example.Main "sqrt(144)"  # Result: 12
```

### Decimal Numbers
```bash
java -cp target/classes org.example.Main "3.14 * 2"   # Result: 6.28
java -cp target/classes org.example.Main "10.5 / 2.5" # Result: 4.2
```

### Negative Numbers
```bash
java -cp target/classes org.example.Main "-5 + 3"     # Result: -2
java -cp target/classes org.example.Main "-10 * -2"   # Result: 20
```

## Error Handling

The calculator handles various error conditions gracefully:

- **Division by zero**: `10 / 0` → "Division by zero is not allowed"
- **Invalid expressions**: `5 +` → "Invalid expression format"
- **Unsupported operators**: `5 & 3` → "Unsupported operator: &"
- **Invalid numbers**: `abc + 3` → "Invalid number format"
- **Negative square root**: `sqrt(-1)` → "Cannot calculate square root of negative number"

## Testing

The application includes comprehensive test coverage:

```bash
# Run all tests
mvn test

# Run tests with coverage report (if configured)
mvn test jacoco:report

# Run specific test class
mvn test -Dtest=CalculatorTest
mvn test -Dtest=MainTest
```

### Test Coverage
- **Unit Tests**: Complete coverage of Calculator class methods
- **Integration Tests**: Command-line interface functionality
- **Edge Cases**: Error conditions and boundary values
- **Input Validation**: Malformed expressions and invalid data

## Project Structure

```
src/
├── main/java/org/example/
│   ├── Calculator.java      # Core calculator logic
│   └── Main.java           # Command-line interface
└── test/java/org/example/
    ├── CalculatorTest.java  # Unit tests for Calculator
    └── MainTest.java       # Integration tests for Main
```

## Architecture

### Calculator Class
- Pure functions for mathematical operations
- Stateless design for thread safety
- Comprehensive error handling
- Expression parsing and evaluation

### Main Class
- Command-line argument processing
- Interactive mode with user-friendly interface
- Input validation and error reporting
- Cross-platform compatibility

## Contributing

1. Fork the repository
2. Create a feature branch
3. Add tests for new functionality
4. Ensure all tests pass
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Future Enhancements

Potential improvements for future versions:
- Support for parentheses in expressions
- Memory functions (store/recall)
- History of calculations
- Scientific functions (trigonometry, logarithms)
- GUI interface
- Configuration file support
- Plugin architecture for custom operations
