/*
 * Copyright 2024
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jsonparser;

/**
 * A JSON parser that can parse JSON strings into JSONValue objects.
 */
public class JSONParser {
    
    private String json;
    private int position;
    private int length;
    
    /**
     * Parses a JSON string and returns the corresponding JSONValue.
     */
    public static JSONValue parse(String json) throws JSONException {
        if (json == null) {
            throw new JSONException("JSON string cannot be null");
        }
        
        JSONParser parser = new JSONParser();
        return parser.parseInternal(json);
    }
    
    private JSONValue parseInternal(String json) throws JSONException {
        this.json = json;
        this.position = 0;
        this.length = json.length();
        
        skipWhitespace();
        
        if (position >= length) {
            throw new JSONException("Empty JSON string", position);
        }
        
        JSONValue result = parseValue();
        
        skipWhitespace();
        if (position < length) {
            throw new JSONException("Unexpected characters after JSON value", position);
        }
        
        return result;
    }
    
    private JSONValue parseValue() throws JSONException {
        skipWhitespace();
        
        if (position >= length) {
            throw new JSONException("Unexpected end of JSON", position);
        }
        
        char c = json.charAt(position);
        
        switch (c) {
            case '{':
                return parseObject();
            case '[':
                return parseArray();
            case '"':
                return parseString();
            case 't':
            case 'f':
                return parseBoolean();
            case 'n':
                return parseNull();
            default:
                if (c == '-' || Character.isDigit(c)) {
                    return parseNumber();
                } else {
                    throw new JSONException("Unexpected character: " + c, position);
                }
        }
    }
    
    private JSONObject parseObject() throws JSONException {
        JSONObject obj = new JSONObject();
        
        consume('{');
        skipWhitespace();
        
        // Handle empty object
        if (position < length && json.charAt(position) == '}') {
            consume('}');
            return obj;
        }
        
        while (true) {
            skipWhitespace();
            
            // Parse key
            if (position >= length || json.charAt(position) != '"') {
                throw new JSONException("Expected string key", position);
            }
            
            JSONString keyValue = parseString();
            String key = keyValue.getValue();
            
            skipWhitespace();
            consume(':');
            skipWhitespace();
            
            // Parse value
            JSONValue value = parseValue();
            obj.put(key, value);
            
            skipWhitespace();
            
            if (position >= length) {
                throw new JSONException("Unexpected end of JSON in object", position);
            }
            
            char c = json.charAt(position);
            if (c == '}') {
                consume('}');
                break;
            } else if (c == ',') {
                consume(',');
            } else {
                throw new JSONException("Expected ',' or '}' in object", position);
            }
        }
        
        return obj;
    }
    
    private JSONArray parseArray() throws JSONException {
        JSONArray array = new JSONArray();
        
        consume('[');
        skipWhitespace();
        
        // Handle empty array
        if (position < length && json.charAt(position) == ']') {
            consume(']');
            return array;
        }
        
        while (true) {
            skipWhitespace();
            
            JSONValue value = parseValue();
            array.add(value);
            
            skipWhitespace();
            
            if (position >= length) {
                throw new JSONException("Unexpected end of JSON in array", position);
            }
            
            char c = json.charAt(position);
            if (c == ']') {
                consume(']');
                break;
            } else if (c == ',') {
                consume(',');
            } else {
                throw new JSONException("Expected ',' or ']' in array", position);
            }
        }
        
        return array;
    }
    
    private JSONString parseString() throws JSONException {
        consume('"');
        
        StringBuilder sb = new StringBuilder();
        
        while (position < length) {
            char c = json.charAt(position);
            
            if (c == '"') {
                consume('"');
                return new JSONString(sb.toString());
            } else if (c == '\\') {
                position++;
                if (position >= length) {
                    throw new JSONException("Unexpected end of JSON in string escape", position);
                }
                
                char escaped = json.charAt(position);
                switch (escaped) {
                    case '"':
                        sb.append('"');
                        break;
                    case '\\':
                        sb.append('\\');
                        break;
                    case '/':
                        sb.append('/');
                        break;
                    case 'b':
                        sb.append('\b');
                        break;
                    case 'f':
                        sb.append('\f');
                        break;
                    case 'n':
                        sb.append('\n');
                        break;
                    case 'r':
                        sb.append('\r');
                        break;
                    case 't':
                        sb.append('\t');
                        break;
                    case 'u':
                        // Unicode escape sequence
                        if (position + 4 >= length) {
                            throw new JSONException("Incomplete unicode escape sequence", position);
                        }
                        String hex = json.substring(position + 1, position + 5);
                        try {
                            int codePoint = Integer.parseInt(hex, 16);
                            sb.append((char) codePoint);
                            position += 4;
                        } catch (NumberFormatException e) {
                            throw new JSONException("Invalid unicode escape sequence: \\u" + hex, position);
                        }
                        break;
                    default:
                        throw new JSONException("Invalid escape sequence: \\" + escaped, position);
                }
                position++;
            } else if (c < 32) {
                throw new JSONException("Unescaped control character in string", position);
            } else {
                sb.append(c);
                position++;
            }
        }
        
        throw new JSONException("Unterminated string", position);
    }
    
    private JSONNumber parseNumber() throws JSONException {
        int start = position;
        
        // Handle negative sign
        if (position < length && json.charAt(position) == '-') {
            position++;
        }
        
        if (position >= length || !Character.isDigit(json.charAt(position))) {
            throw new JSONException("Invalid number format", position);
        }
        
        // Parse integer part
        if (json.charAt(position) == '0') {
            position++;
        } else {
            while (position < length && Character.isDigit(json.charAt(position))) {
                position++;
            }
        }
        
        // Parse decimal part
        if (position < length && json.charAt(position) == '.') {
            position++;
            if (position >= length || !Character.isDigit(json.charAt(position))) {
                throw new JSONException("Invalid number format: missing digits after decimal point", position);
            }
            while (position < length && Character.isDigit(json.charAt(position))) {
                position++;
            }
        }
        
        // Parse exponent part
        if (position < length && (json.charAt(position) == 'e' || json.charAt(position) == 'E')) {
            position++;
            if (position < length && (json.charAt(position) == '+' || json.charAt(position) == '-')) {
                position++;
            }
            if (position >= length || !Character.isDigit(json.charAt(position))) {
                throw new JSONException("Invalid number format: missing digits in exponent", position);
            }
            while (position < length && Character.isDigit(json.charAt(position))) {
                position++;
            }
        }
        
        String numberStr = json.substring(start, position);
        try {
            return new JSONNumber(numberStr);
        } catch (NumberFormatException e) {
            throw new JSONException("Invalid number format: " + numberStr, start);
        }
    }
    
    private JSONBoolean parseBoolean() throws JSONException {
        if (position + 4 <= length && json.substring(position, position + 4).equals("true")) {
            position += 4;
            return JSONBoolean.TRUE;
        } else if (position + 5 <= length && json.substring(position, position + 5).equals("false")) {
            position += 5;
            return JSONBoolean.FALSE;
        } else {
            throw new JSONException("Invalid boolean value", position);
        }
    }
    
    private JSONNull parseNull() throws JSONException {
        if (position + 4 <= length && json.substring(position, position + 4).equals("null")) {
            position += 4;
            return JSONNull.INSTANCE;
        } else {
            throw new JSONException("Invalid null value", position);
        }
    }
    
    private void consume(char expected) throws JSONException {
        if (position >= length || json.charAt(position) != expected) {
            throw new JSONException("Expected '" + expected + "'", position);
        }
        position++;
    }
    
    private void skipWhitespace() {
        while (position < length && Character.isWhitespace(json.charAt(position))) {
            position++;
        }
    }
}