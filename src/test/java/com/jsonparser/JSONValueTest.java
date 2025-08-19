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
 * Unit tests for JSON value classes.
 */
public class JSONValueTest {
    
    @Test
    public void testJSONString() {
        JSONString str = new JSONString("hello");
        assertEquals("hello", str.getValue());
        assertEquals("\"hello\"", str.toJSONString());
        assertEquals("hello", str.toString());
        assertEquals(JSONValue.JSONType.STRING, str.getType());
    }
    
    @Test
    public void testJSONStringWithNull() {
        JSONString str = new JSONString(null);
        assertEquals("", str.getValue());
        assertEquals("\"\"", str.toJSONString());
    }
    
    @Test
    public void testJSONNumber() {
        JSONNumber num1 = new JSONNumber(42);
        assertEquals(42, num1.intValue());
        assertEquals(42L, num1.longValue());
        assertEquals(42.0, num1.doubleValue(), 0.001);
        assertEquals("42", num1.toJSONString());
        assertEquals(JSONValue.JSONType.NUMBER, num1.getType());
        assertTrue(num1.isInteger());
        
        JSONNumber num2 = new JSONNumber(3.14);
        assertEquals(3.14, num2.doubleValue(), 0.001);
        assertEquals("3.14", num2.toJSONString());
        assertFalse(num2.isInteger());
    }
    
    @Test
    public void testJSONNumberFromString() throws NumberFormatException {
        JSONNumber num1 = new JSONNumber("123");
        assertEquals(123, num1.intValue());
        assertTrue(num1.isInteger());
        
        JSONNumber num2 = new JSONNumber("45.67");
        assertEquals(45.67, num2.doubleValue(), 0.001);
        assertFalse(num2.isInteger());
        
        JSONNumber num3 = new JSONNumber("1.23e-4");
        assertEquals(0.000123, num3.doubleValue(), 0.0000001);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testJSONNumberWithNull() {
        new JSONNumber((Number) null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testJSONNumberWithNullString() {
        new JSONNumber((String) null);
    }
    
    @Test(expected = NumberFormatException.class)
    public void testJSONNumberWithInvalidString() {
        new JSONNumber("not a number");
    }
    
    @Test
    public void testJSONBoolean() {
        JSONBoolean trueVal = JSONBoolean.valueOf(true);
        assertTrue(trueVal.getValue());
        assertEquals("true", trueVal.toJSONString());
        assertEquals("true", trueVal.toString());
        assertEquals(JSONValue.JSONType.BOOLEAN, trueVal.getType());
        assertSame(JSONBoolean.TRUE, trueVal);
        
        JSONBoolean falseVal = JSONBoolean.valueOf(false);
        assertFalse(falseVal.getValue());
        assertEquals("false", falseVal.toJSONString());
        assertEquals("false", falseVal.toString());
        assertSame(JSONBoolean.FALSE, falseVal);
    }
    
    @Test
    public void testJSONNull() {
        JSONNull nullVal = JSONNull.INSTANCE;
        assertEquals("null", nullVal.toJSONString());
        assertEquals("null", nullVal.toString());
        assertEquals(JSONValue.JSONType.NULL, nullVal.getType());
    }
    
    @Test
    public void testEqualsAndHashCode() {
        // Test JSONString
        JSONString str1 = new JSONString("test");
        JSONString str2 = new JSONString("test");
        JSONString str3 = new JSONString("different");
        
        assertEquals(str1, str2);
        assertNotEquals(str1, str3);
        assertEquals(str1.hashCode(), str2.hashCode());
        
        // Test JSONNumber
        JSONNumber num1 = new JSONNumber(42);
        JSONNumber num2 = new JSONNumber(42);
        JSONNumber num3 = new JSONNumber(43);
        
        assertEquals(num1, num2);
        assertNotEquals(num1, num3);
        assertEquals(num1.hashCode(), num2.hashCode());
        
        // Test JSONBoolean
        assertEquals(JSONBoolean.TRUE, JSONBoolean.valueOf(true));
        assertEquals(JSONBoolean.FALSE, JSONBoolean.valueOf(false));
        assertNotEquals(JSONBoolean.TRUE, JSONBoolean.FALSE);
        
        // Test JSONNull
        assertEquals(JSONNull.INSTANCE, JSONNull.INSTANCE);
        JSONNull anotherNull = JSONNull.INSTANCE;
        assertEquals(JSONNull.INSTANCE, anotherNull);
    }
}