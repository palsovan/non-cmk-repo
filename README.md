# JSON Parser

A lightweight JSON parser implementation in Java that can parse JSON strings into Java objects and serialize Java objects back to JSON strings.

## Features

- **Complete JSON Support**: Parses all JSON data types (objects, arrays, strings, numbers, booleans, null)
- **Error Handling**: Comprehensive error reporting with position information
- **Type Safety**: Strongly typed JSON value classes
- **Standards Compliant**: Follows JSON specification (RFC 7159)
- **No Dependencies**: Pure Java implementation with no external dependencies
- **Extensible**: Clean object-oriented design for easy extension

## Project Structure

```
src/
├── main/java/com/jsonparser/
│   ├── JSONParser.java      # Main parser class
│   ├── JSONValue.java       # Base interface for JSON values
│   ├── JSONObject.java      # JSON object implementation
│   ├── JSONArray.java       # JSON array implementation
│   ├── JSONString.java      # JSON string implementation
│   ├── JSONNumber.java      # JSON number implementation
│   ├── JSONBoolean.java     # JSON boolean implementation
│   ├── JSONNull.java        # JSON null implementation
│   ├── JSONException.java   # Exception class for parsing errors
│   └── JSONParserDemo.java  # Demo application
└── test/java/com/jsonparser/
    ├── JSONParserTest.java  # Parser tests
    ├── JSONObjectTest.java  # Object tests
    ├── JSONArrayTest.java   # Array tests
    └── JSONValueTest.java   # Value type tests
```

## Building

This project uses Apache Ant for building. Make sure you have Ant installed.

### Available Targets

- `ant compile` - Compile the source code
- `ant jar` - Create JAR file
- `ant demo` - Run the demo application
- `ant test-if-available` - Run tests if JUnit is available
- `ant clean` - Clean build artifacts
- `ant help` - Show all available targets

### Quick Start

```bash
# Compile the project
ant compile

# Run the demo
ant demo

# Create JAR file
ant jar

# Run the JAR
java -jar dist/json-parser-1.0.0.jar '{"hello": "world"}'
```

## Usage

### Parsing JSON

```java
import com.jsonparser.*;

// Parse a JSON string
String jsonString = "{\"name\": \"John\", \"age\": 30, \"active\": true}";
JSONValue result = JSONParser.parse(jsonString);

// Cast to appropriate type
JSONObject obj = (JSONObject) result;
String name = ((JSONString) obj.get("name")).getValue();
int age = ((JSONNumber) obj.get("age")).intValue();
boolean active = ((JSONBoolean) obj.get("active")).getValue();
```

### Creating JSON

```java
// Create JSON object programmatically
JSONObject person = new JSONObject();
person.put("name", new JSONString("Alice"));
person.put("age", new JSONNumber(25));
person.put("hobbies", new JSONArray());

JSONArray hobbies = (JSONArray) person.get("hobbies");
hobbies.add(new JSONString("reading"));
hobbies.add(new JSONString("coding"));

// Convert to JSON string
String json = person.toJSONString();
// Output: {"name":"Alice","age":25,"hobbies":["reading","coding"]}
```

### Supported JSON Types

| JSON Type | Java Class | Example |
|-----------|------------|---------|
| Object | `JSONObject` | `{"key": "value"}` |
| Array | `JSONArray` | `[1, 2, 3]` |
| String | `JSONString` | `"hello"` |
| Number | `JSONNumber` | `42`, `3.14`, `1.23e-4` |
| Boolean | `JSONBoolean` | `true`, `false` |
| Null | `JSONNull` | `null` |

## Testing

The project includes comprehensive unit tests. To run them:

1. Download JUnit 4.x JAR file
2. Place it in the `lib/` directory
3. Run `ant test`

If JUnit is not available, you can still compile and run the demo:
```bash
ant test-if-available  # Skips tests if JUnit not found
```

## Examples

### Complex JSON Parsing

```java
String complexJson = """
{
  "users": [
    {
      "id": 1,
      "name": "John Doe",
      "address": {
        "street": "123 Main St",
        "city": "New York"
      }
    }
  ],
  "total": 1
}
""";

JSONObject root = (JSONObject) JSONParser.parse(complexJson);
JSONArray users = (JSONArray) root.get("users");
JSONObject firstUser = (JSONObject) users.get(0);
JSONObject address = (JSONObject) firstUser.get("address");
String city = ((JSONString) address.get("city")).getValue();
```

### Error Handling

```java
try {
    JSONValue result = JSONParser.parse("{invalid json}");
} catch (JSONException e) {
    System.err.println("Parse error: " + e.getMessage());
    // Output: Parse error: Expected string key at position 1
}
```

## License

This project is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Add tests for new functionality
4. Ensure all tests pass
5. Submit a pull request