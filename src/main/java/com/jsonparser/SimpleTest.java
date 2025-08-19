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
 * Simple test to verify JSON parser functionality.
 */
public class SimpleTest {
    
    public static void main(String[] args) {
        System.out.println("JSON Parser Simple Test");
        System.out.println("=======================");
        
        // Test 1: Simple object
        testSimpleObject();
        
        // Test 2: Simple array
        testSimpleArray();
        
        // Test 3: Mixed types
        testMixedTypes();
        
        // Test 4: Error handling
        testErrorHandling();
        
        System.out.println("\nAll tests completed successfully!");
    }
    
    private static void testSimpleObject() {
        System.out.println("\nTest 1: Simple Object");
        try {
            String json = "{\"name\": \"John\", \"age\": 30}";
            JSONValue result = JSONParser.parse(json);
            System.out.println("Input:  " + json);
            System.out.println("Output: " + result.toJSONString());
            System.out.println("Type:   " + result.getType());
            
            if (result instanceof JSONObject) {
                JSONObject obj = (JSONObject) result;
                System.out.println("Name: " + ((JSONString) obj.get("name")).getValue());
                System.out.println("Age:  " + ((JSONNumber) obj.get("age")).intValue());
            }
            System.out.println("✓ PASSED");
        } catch (Exception e) {
            System.out.println("✗ FAILED: " + e.getMessage());
        }
    }
    
    private static void testSimpleArray() {
        System.out.println("\nTest 2: Simple Array");
        try {
            String json = "[1, 2, 3, \"hello\", true, null]";
            JSONValue result = JSONParser.parse(json);
            System.out.println("Input:  " + json);
            System.out.println("Output: " + result.toJSONString());
            System.out.println("Type:   " + result.getType());
            
            if (result instanceof JSONArray) {
                JSONArray arr = (JSONArray) result;
                System.out.println("Size: " + arr.size());
                for (int i = 0; i < arr.size(); i++) {
                    System.out.println("  [" + i + "]: " + arr.get(i).toJSONString() + " (" + arr.get(i).getType() + ")");
                }
            }
            System.out.println("✓ PASSED");
        } catch (Exception e) {
            System.out.println("✗ FAILED: " + e.getMessage());
        }
    }
    
    private static void testMixedTypes() {
        System.out.println("\nTest 3: Mixed Types");
        try {
            String json = "{\"users\": [{\"name\": \"John\", \"active\": true}, {\"name\": \"Jane\", \"active\": false}], \"count\": 2}";
            JSONValue result = JSONParser.parse(json);
            System.out.println("Input:  " + json);
            System.out.println("Output: " + result.toJSONString());
            System.out.println("Type:   " + result.getType());
            System.out.println("✓ PASSED");
        } catch (Exception e) {
            System.out.println("✗ FAILED: " + e.getMessage());
        }
    }
    
    private static void testErrorHandling() {
        System.out.println("\nTest 4: Error Handling");
        try {
            String json = "{invalid json}";
            JSONValue result = JSONParser.parse(json);
            System.out.println("✗ FAILED: Should have thrown exception");
        } catch (JSONException e) {
            System.out.println("Input:  {invalid json}");
            System.out.println("Error:  " + e.getMessage());
            System.out.println("✓ PASSED - Exception caught as expected");
        } catch (Exception e) {
            System.out.println("✗ FAILED: Wrong exception type: " + e.getMessage());
        }
    }
}