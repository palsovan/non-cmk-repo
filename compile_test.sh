#!/bin/bash

echo "JSON Parser Compilation Test"
echo "============================"

# Create build directory
mkdir -p build/classes

# Compile all Java files
echo "Compiling Java files..."
javac -d build/classes -cp build/classes src/main/java/com/jsonparser/*.java

if [ $? -eq 0 ]; then
    echo "✓ Compilation successful!"
    
    # Run simple test
    echo ""
    echo "Running simple test..."
    java -cp build/classes com.jsonparser.SimpleTest
    
    echo ""
    echo "Running demo..."
    java -cp build/classes com.jsonparser.JSONParserDemo '{"test": "from command line"}'
    
else
    echo "✗ Compilation failed!"
    exit 1
fi