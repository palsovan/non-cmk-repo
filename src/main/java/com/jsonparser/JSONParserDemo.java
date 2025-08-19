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
 * Demo class to showcase JSON parser functionality.
 */
public class JSONParserDemo {
    
    public static void main(String[] args) {
        System.out.println("JSON Parser Demo");
        System.out.println("================");
        
        // Demo 1: Parse simple values
        demonstrateSimpleValues();
        
        // Demo 2: Parse arrays
        demonstrateArrays();
        
        // Demo 3: Parse objects
        demonstrateObjects();
        
        // Demo 4: Parse complex nested structures
        demonstrateComplexStructures();
        
        // Demo 5: Create JSON programmatically
        demonstrateJSONCreation();
        
        // Demo 6: Parse command line argument if provided
        if (args.length > 0) {
            demonstrateCommandLineJSON(args[0]);
        }
    }
    
    private static void demonstrateSimpleValues() {
        System.out.println("\n1. Simple Values:");
        System.out.println("-----------------");
        
        try {
            JSONValue nullValue = JSONParser.parse("null");
            System.out.println("Parsed null: " + nullValue.toJSONString());
            
            JSONValue boolValue = JSONParser.parse("true");
            System.out.println("Parsed boolean: " + boolValue.toJSONString());
            
            JSONValue stringValue = JSONParser.parse("\"Hello, World!\"");
            System.out.println("Parsed string: " + stringValue.toJSONString());
            
            JSONValue numberValue = JSONParser.parse("42.5");
            System.out.println("Parsed number: " + numberValue.toJSONString());
            
        } catch (JSONException e) {
            System.err.println("Error parsing simple values: " + e.getMessage());
        }
    }
    
    private static void demonstrateArrays() {
        System.out.println("\n2. Arrays:");
        System.out.println("----------");
        
        try {
            JSONValue emptyArray = JSONParser.parse("[]");
            System.out.println("Empty array: " + emptyArray.toJSONString());
            
            JSONValue numberArray = JSONParser.parse("[1, 2, 3, 4, 5]");
            System.out.println("Number array: " + numberArray.toJSONString());
            
            JSONValue mixedArray = JSONParser.parse("[\"hello\", 42, true, null]");
            System.out.println("Mixed array: " + mixedArray.toJSONString());
            
        } catch (JSONException e) {
            System.err.println("Error parsing arrays: " + e.getMessage());
        }
    }
    
    private static void demonstrateObjects() {
        System.out.println("\n3. Objects:");
        System.out.println("-----------");
        
        try {
            JSONValue emptyObject = JSONParser.parse("{}");
            System.out.println("Empty object: " + emptyObject.toJSONString());
            
            JSONValue person = JSONParser.parse("{\"name\": \"John Doe\", \"age\": 30, \"active\": true}");
            System.out.println("Person object: " + person.toJSONString());
            
        } catch (JSONException e) {
            System.err.println("Error parsing objects: " + e.getMessage());
        }
    }
    
    private static void demonstrateComplexStructures() {
        System.out.println("\n4. Complex Nested Structures:");
        System.out.println("------------------------------");
        
        try {
            String complexJSON = "{\n" +
                "  \"users\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"John Doe\",\n" +
                "      \"email\": \"john@example.com\",\n" +
                "      \"address\": {\n" +
                "        \"street\": \"123 Main St\",\n" +
                "        \"city\": \"New York\",\n" +
                "        \"zipcode\": \"10001\"\n" +
                "      },\n" +
                "      \"hobbies\": [\"reading\", \"swimming\", \"coding\"]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"name\": \"Jane Smith\",\n" +
                "      \"email\": \"jane@example.com\",\n" +
                "      \"address\": {\n" +
                "        \"street\": \"456 Oak Ave\",\n" +
                "        \"city\": \"Los Angeles\",\n" +
                "        \"zipcode\": \"90210\"\n" +
                "      },\n" +
                "      \"hobbies\": [\"painting\", \"hiking\"]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"total\": 2\n" +
                "}";
            
            JSONValue result = JSONParser.parse(complexJSON);
            System.out.println("Complex structure parsed successfully!");
            System.out.println("Compact JSON: " + result.toJSONString());
            
        } catch (JSONException e) {
            System.err.println("Error parsing complex structure: " + e.getMessage());
        }
    }
    
    private static void demonstrateJSONCreation() {
        System.out.println("\n5. Creating JSON Programmatically:");
        System.out.println("-----------------------------------");
        
        // Create a JSON object programmatically
        JSONObject person = new JSONObject();
        person.put("name", new JSONString("Alice Johnson"));
        person.put("age", new JSONNumber(28));
        person.put("married", JSONBoolean.FALSE);
        person.put("spouse", JSONNull.INSTANCE);
        
        JSONArray skills = new JSONArray();
        skills.add(new JSONString("Java"));
        skills.add(new JSONString("Python"));
        skills.add(new JSONString("JavaScript"));
        person.put("skills", skills);
        
        JSONObject address = new JSONObject();
        address.put("street", new JSONString("789 Pine St"));
        address.put("city", new JSONString("Seattle"));
        address.put("state", new JSONString("WA"));
        person.put("address", address);
        
        System.out.println("Created JSON: " + person.toJSONString());
    }
    
    private static void demonstrateCommandLineJSON(String json) {
        System.out.println("\n6. Command Line JSON:");
        System.out.println("---------------------");
        System.out.println("Input: " + json);
        
        try {
            JSONValue result = JSONParser.parse(json);
            System.out.println("Parsed successfully!");
            System.out.println("Type: " + result.getType());
            System.out.println("Output: " + result.toJSONString());
            
        } catch (JSONException e) {
            System.err.println("Error parsing command line JSON: " + e.getMessage());
        }
    }
}