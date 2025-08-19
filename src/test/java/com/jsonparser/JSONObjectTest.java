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
 * Unit tests for JSONObject.
 */
public class JSONObjectTest {
    
    @Test
    public void testEmptyObject() {
        JSONObject obj = new JSONObject();
        assertTrue(obj.isEmpty());
        assertEquals(0, obj.size());
        assertEquals("{}", obj.toJSONString());
        assertEquals(JSONValue.JSONType.OBJECT, obj.getType());
    }
    
    @Test
    public void testPutAndGet() {
        JSONObject obj = new JSONObject();
        JSONString value = new JSONString("test");
        
        obj.put("key", value);
        assertEquals(1, obj.size());
        assertFalse(obj.isEmpty());
        assertTrue(obj.containsKey("key"));
        assertEquals(value, obj.get("key"));
    }
    
    @Test
    public void testRemove() {
        JSONObject obj = new JSONObject();
        JSONString value = new JSONString("test");
        
        obj.put("key", value);
        assertEquals(value, obj.remove("key"));
        assertFalse(obj.containsKey("key"));
        assertEquals(0, obj.size());
        assertNull(obj.get("key"));
    }
    
    @Test
    public void testKeySet() {
        JSONObject obj = new JSONObject();
        obj.put("key1", new JSONString("value1"));
        obj.put("key2", new JSONString("value2"));
        
        assertEquals(2, obj.keySet().size());
        assertTrue(obj.keySet().contains("key1"));
        assertTrue(obj.keySet().contains("key2"));
    }
    
    @Test
    public void testToJSONStringWithMultipleProperties() {
        JSONObject obj = new JSONObject();
        obj.put("name", new JSONString("John"));
        obj.put("age", new JSONNumber(30));
        obj.put("active", JSONBoolean.TRUE);
        
        String json = obj.toJSONString();
        assertTrue(json.contains("\"name\":\"John\""));
        assertTrue(json.contains("\"age\":30"));
        assertTrue(json.contains("\"active\":true"));
        assertTrue(json.startsWith("{"));
        assertTrue(json.endsWith("}"));
    }
    
    @Test
    public void testToJSONStringWithSpecialCharacters() {
        JSONObject obj = new JSONObject();
        obj.put("quote", new JSONString("He said \"Hello\""));
        obj.put("newline", new JSONString("Line 1\nLine 2"));
        obj.put("tab", new JSONString("Column1\tColumn2"));
        
        String json = obj.toJSONString();
        assertTrue(json.contains("\"He said \\\"Hello\\\"\""));
        assertTrue(json.contains("\"Line 1\\nLine 2\""));
        assertTrue(json.contains("\"Column1\\tColumn2\""));
    }
    
    @Test
    public void testEquals() {
        JSONObject obj1 = new JSONObject();
        obj1.put("key", new JSONString("value"));
        
        JSONObject obj2 = new JSONObject();
        obj2.put("key", new JSONString("value"));
        
        JSONObject obj3 = new JSONObject();
        obj3.put("key", new JSONString("different"));
        
        assertEquals(obj1, obj2);
        assertNotEquals(obj1, obj3);
        assertNotEquals(obj1, null);
        assertNotEquals(obj1, "not an object");
    }
    
    @Test
    public void testHashCode() {
        JSONObject obj1 = new JSONObject();
        obj1.put("key", new JSONString("value"));
        
        JSONObject obj2 = new JSONObject();
        obj2.put("key", new JSONString("value"));
        
        assertEquals(obj1.hashCode(), obj2.hashCode());
    }
}