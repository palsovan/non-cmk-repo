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

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for JSONParser.
 */
public class JSONParserTest {
    
    @Test
    public void testParseNull() throws JSONException {
        JSONValue result = JSONParser.parse("null");
        assertTrue(result instanceof JSONNull);
        assertEquals("null", result.toJSONString());
    }
    
    @Test
    public void testParseBoolean() throws JSONException {
        JSONValue trueResult = JSONParser.parse("true");
        assertTrue(trueResult instanceof JSONBoolean);
        assertTrue(((JSONBoolean) trueResult).getValue());
        assertEquals("true", trueResult.toJSONString());
        
        JSONValue falseResult = JSONParser.parse("false");
        assertTrue(falseResult instanceof JSONBoolean);
        assertFalse(((JSONBoolean) falseResult).getValue());
        assertEquals("false", falseResult.toJSONString());
    }
    
    @Test
    public void testParseString() throws JSONException {
        JSONValue result = JSONParser.parse("\"hello world\"");
        assertTrue(result instanceof JSONString);
        assertEquals("hello world", ((JSONString) result).getValue());
        assertEquals("\"hello world\"", result.toJSONString());
    }
    
    @Test
    public void testParseStringWithEscapes() throws JSONException {
        JSONValue result = JSONParser.parse("\"hello\\nworld\\t!\"");
        assertTrue(result instanceof JSONString);
        assertEquals("hello\nworld\t!", ((JSONString) result).getValue());
        
        JSONValue result2 = JSONParser.parse("\"quote: \\\"test\\\"\"");
        assertEquals("quote: \"test\"", ((JSONString) result2).getValue());
    }
    
    @Test
    public void testParseStringWithUnicodeEscape() throws JSONException {
        JSONValue result = JSONParser.parse("\"\\u0048\\u0065\\u006C\\u006C\\u006F\"");
        assertTrue(result instanceof JSONString);
        assertEquals("Hello", ((JSONString) result).getValue());
    }
    
    @Test
    public void testParseNumber() throws JSONException {
        JSONValue result1 = JSONParser.parse("42");
        assertTrue(result1 instanceof JSONNumber);
        assertEquals(42, ((JSONNumber) result1).intValue());
        
        JSONValue result2 = JSONParser.parse("-17");
        assertTrue(result2 instanceof JSONNumber);
        assertEquals(-17, ((JSONNumber) result2).intValue());
        
        JSONValue result3 = JSONParser.parse("3.14159");
        assertTrue(result3 instanceof JSONNumber);
        assertEquals(3.14159, ((JSONNumber) result3).doubleValue(), 0.00001);
        
        JSONValue result4 = JSONParser.parse("1.23e-4");
        assertTrue(result4 instanceof JSONNumber);
        assertEquals(0.000123, ((JSONNumber) result4).doubleValue(), 0.0000001);
    }
    
    @Test
    public void testParseEmptyArray() throws JSONException {
        JSONValue result = JSONParser.parse("[]");
        assertTrue(result instanceof JSONArray);
        JSONArray array = (JSONArray) result;
        assertEquals(0, array.size());
        assertEquals("[]", result.toJSONString());
    }
    
    @Test
    public void testParseArray() throws JSONException {
        JSONValue result = JSONParser.parse("[1, 2, 3]");
        assertTrue(result instanceof JSONArray);
        JSONArray array = (JSONArray) result;
        assertEquals(3, array.size());
        
        assertTrue(array.get(0) instanceof JSONNumber);
        assertEquals(1, ((JSONNumber) array.get(0)).intValue());
        
        assertTrue(array.get(1) instanceof JSONNumber);
        assertEquals(2, ((JSONNumber) array.get(1)).intValue());
        
        assertTrue(array.get(2) instanceof JSONNumber);
        assertEquals(3, ((JSONNumber) array.get(2)).intValue());
    }
    
    @Test
    public void testParseMixedArray() throws JSONException {
        JSONValue result = JSONParser.parse("[\"hello\", 42, true, null]");
        assertTrue(result instanceof JSONArray);
        JSONArray array = (JSONArray) result;
        assertEquals(4, array.size());
        
        assertTrue(array.get(0) instanceof JSONString);
        assertEquals("hello", ((JSONString) array.get(0)).getValue());
        
        assertTrue(array.get(1) instanceof JSONNumber);
        assertEquals(42, ((JSONNumber) array.get(1)).intValue());
        
        assertTrue(array.get(2) instanceof JSONBoolean);
        assertTrue(((JSONBoolean) array.get(2)).getValue());
        
        assertTrue(array.get(3) instanceof JSONNull);
    }
    
    @Test
    public void testParseEmptyObject() throws JSONException {
        JSONValue result = JSONParser.parse("{}");
        assertTrue(result instanceof JSONObject);
        JSONObject obj = (JSONObject) result;
        assertEquals(0, obj.size());
        assertEquals("{}", result.toJSONString());
    }
    
    @Test
    public void testParseObject() throws JSONException {
        JSONValue result = JSONParser.parse("{\"name\": \"John\", \"age\": 30}");
        assertTrue(result instanceof JSONObject);
        JSONObject obj = (JSONObject) result;
        assertEquals(2, obj.size());
        
        assertTrue(obj.containsKey("name"));
        assertTrue(obj.get("name") instanceof JSONString);
        assertEquals("John", ((JSONString) obj.get("name")).getValue());
        
        assertTrue(obj.containsKey("age"));
        assertTrue(obj.get("age") instanceof JSONNumber);
        assertEquals(30, ((JSONNumber) obj.get("age")).intValue());
    }
    
    @Test
    public void testParseNestedObject() throws JSONException {
        String json = "{\"person\": {\"name\": \"John\", \"address\": {\"city\": \"NYC\"}}}";
        JSONValue result = JSONParser.parse(json);
        assertTrue(result instanceof JSONObject);
        JSONObject obj = (JSONObject) result;
        
        assertTrue(obj.containsKey("person"));
        JSONValue person = obj.get("person");
        assertTrue(person instanceof JSONObject);
        JSONObject personObj = (JSONObject) person;
        
        assertTrue(personObj.containsKey("name"));
        assertEquals("John", ((JSONString) personObj.get("name")).getValue());
        
        assertTrue(personObj.containsKey("address"));
        JSONValue address = personObj.get("address");
        assertTrue(address instanceof JSONObject);
        JSONObject addressObj = (JSONObject) address;
        
        assertTrue(addressObj.containsKey("city"));
        assertEquals("NYC", ((JSONString) addressObj.get("city")).getValue());
    }
    
    @Test
    public void testParseArrayOfObjects() throws JSONException {
        String json = "[{\"name\": \"John\"}, {\"name\": \"Jane\"}]";
        JSONValue result = JSONParser.parse(json);
        assertTrue(result instanceof JSONArray);
        JSONArray array = (JSONArray) result;
        assertEquals(2, array.size());
        
        assertTrue(array.get(0) instanceof JSONObject);
        JSONObject obj1 = (JSONObject) array.get(0);
        assertEquals("John", ((JSONString) obj1.get("name")).getValue());
        
        assertTrue(array.get(1) instanceof JSONObject);
        JSONObject obj2 = (JSONObject) array.get(1);
        assertEquals("Jane", ((JSONString) obj2.get("name")).getValue());
    }
    
    @Test
    public void testParseWithWhitespace() throws JSONException {
        String json = "  {  \"name\"  :  \"John\"  ,  \"age\"  :  30  }  ";
        JSONValue result = JSONParser.parse(json);
        assertTrue(result instanceof JSONObject);
        JSONObject obj = (JSONObject) result;
        assertEquals("John", ((JSONString) obj.get("name")).getValue());
        assertEquals(30, ((JSONNumber) obj.get("age")).intValue());
    }
    
    @Test(expected = JSONException.class)
    public void testParseNullString() throws JSONException {
        JSONParser.parse(null);
    }
    
    @Test(expected = JSONException.class)
    public void testParseEmptyString() throws JSONException {
        JSONParser.parse("");
    }
    
    @Test(expected = JSONException.class)
    public void testParseInvalidJSON() throws JSONException {
        JSONParser.parse("{invalid}");
    }
    
    @Test(expected = JSONException.class)
    public void testParseUnterminatedString() throws JSONException {
        JSONParser.parse("\"unterminated");
    }
    
    @Test(expected = JSONException.class)
    public void testParseInvalidNumber() throws JSONException {
        JSONParser.parse("123.45.67");
    }
    
    @Test(expected = JSONException.class)
    public void testParseTrailingCommaInArray() throws JSONException {
        JSONParser.parse("[1, 2, 3,]");
    }
    
    @Test(expected = JSONException.class)
    public void testParseTrailingCommaInObject() throws JSONException {
        JSONParser.parse("{\"key\": \"value\",}");
    }
    
    @Test(expected = JSONException.class)
    public void testParseMissingColon() throws JSONException {
        JSONParser.parse("{\"key\" \"value\"}");
    }
    
    @Test(expected = JSONException.class)
    public void testParseExtraCharacters() throws JSONException {
        JSONParser.parse("true false");
    }
}