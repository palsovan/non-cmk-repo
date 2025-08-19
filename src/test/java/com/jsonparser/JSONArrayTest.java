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
 * Unit tests for JSONArray.
 */
public class JSONArrayTest {
    
    @Test
    public void testEmptyArray() {
        JSONArray array = new JSONArray();
        assertTrue(array.isEmpty());
        assertEquals(0, array.size());
        assertEquals("[]", array.toJSONString());
        assertEquals(JSONValue.JSONType.ARRAY, array.getType());
    }
    
    @Test
    public void testAddAndGet() {
        JSONArray array = new JSONArray();
        JSONString value = new JSONString("test");
        
        array.add(value);
        assertEquals(1, array.size());
        assertFalse(array.isEmpty());
        assertEquals(value, array.get(0));
        assertTrue(array.contains(value));
    }
    
    @Test
    public void testSetAndRemove() {
        JSONArray array = new JSONArray();
        JSONString value1 = new JSONString("test1");
        JSONString value2 = new JSONString("test2");
        
        array.add(value1);
        array.set(0, value2);
        assertEquals(value2, array.get(0));
        
        assertEquals(value2, array.remove(0));
        assertEquals(0, array.size());
        assertTrue(array.isEmpty());
    }
    
    @Test
    public void testToJSONStringWithMultipleElements() {
        JSONArray array = new JSONArray();
        array.add(new JSONString("hello"));
        array.add(new JSONNumber(42));
        array.add(JSONBoolean.TRUE);
        array.add(JSONNull.INSTANCE);
        
        String json = array.toJSONString();
        assertEquals("[\"hello\",42,true,null]", json);
    }
    
    @Test
    public void testIterator() {
        JSONArray array = new JSONArray();
        array.add(new JSONString("first"));
        array.add(new JSONString("second"));
        array.add(new JSONString("third"));
        
        int count = 0;
        for (JSONValue value : array) {
            assertTrue(value instanceof JSONString);
            count++;
        }
        assertEquals(3, count);
    }
    
    @Test
    public void testEquals() {
        JSONArray array1 = new JSONArray();
        array1.add(new JSONString("value"));
        
        JSONArray array2 = new JSONArray();
        array2.add(new JSONString("value"));
        
        JSONArray array3 = new JSONArray();
        array3.add(new JSONString("different"));
        
        assertEquals(array1, array2);
        assertNotEquals(array1, array3);
        assertNotEquals(array1, null);
        assertNotEquals(array1, "not an array");
    }
    
    @Test
    public void testHashCode() {
        JSONArray array1 = new JSONArray();
        array1.add(new JSONString("value"));
        
        JSONArray array2 = new JSONArray();
        array2.add(new JSONString("value"));
        
        assertEquals(array1.hashCode(), array2.hashCode());
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetOutOfBounds() {
        JSONArray array = new JSONArray();
        array.get(0);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetOutOfBounds() {
        JSONArray array = new JSONArray();
        array.set(0, new JSONString("test"));
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveOutOfBounds() {
        JSONArray array = new JSONArray();
        array.remove(0);
    }
}