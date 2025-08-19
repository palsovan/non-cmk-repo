#!/bin/bash

# Demo script for Java Calculator Application
echo "=== Java Calculator Application Demo ==="
echo

# Compile the application
echo "1. Compiling the application..."
mvn compile -q
if [ $? -eq 0 ]; then
    echo "✅ Compilation successful!"
else
    echo "❌ Compilation failed!"
    exit 1
fi
echo

# Run tests
echo "2. Running tests..."
mvn test -q
if [ $? -eq 0 ]; then
    echo "✅ All tests passed!"
else
    echo "❌ Some tests failed!"
    exit 1
fi
echo

# Demo command-line calculations
echo "3. Demo: Command-line calculations"
echo

echo "Basic arithmetic:"
java -cp target/classes org.example.Main "5 + 3"
echo
java -cp target/classes org.example.Main "10 - 4"
echo
java -cp target/classes org.example.Main "6 * 7"
echo
java -cp target/classes org.example.Main "15 / 3"
echo

echo "Advanced operations:"
java -cp target/classes org.example.Main "100 % 20"
echo
java -cp target/classes org.example.Main "2 ^ 3"
echo
java -cp target/classes org.example.Main "sqrt(25)"
echo

echo "Decimal numbers:"
java -cp target/classes org.example.Main "3.14 * 2"
echo
java -cp target/classes org.example.Main "10.5 / 2.5"
echo

echo "Negative numbers:"
java -cp target/classes org.example.Main "-5 + 3"
echo
java -cp target/classes org.example.Main "-10 * -2"
echo

echo "Error handling examples:"
echo "Division by zero:"
java -cp target/classes org.example.Main "10 / 0" 2>&1
echo

echo "Invalid expression:"
java -cp target/classes org.example.Main "5 +" 2>&1
echo

echo "Unsupported operator:"
java -cp target/classes org.example.Main "5 & 3" 2>&1
echo

echo "=== Demo completed! ==="
echo "To start interactive mode, run:"
echo "java -cp target/classes org.example.Main"