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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a JSON array.
 */
public class JSONArray implements JSONValue, Iterable<JSONValue> {
    
    private final List<JSONValue> elements;
    
    public JSONArray() {
        this.elements = new ArrayList<>();
    }
    
    public JSONArray(List<JSONValue> elements) {
        this.elements = new ArrayList<>(elements);
    }
    
    /**
     * Adds an element to this array.
     */
    public void add(JSONValue value) {
        elements.add(value);
    }
    
    /**
     * Gets the element at the specified index.
     */
    public JSONValue get(int index) {
        return elements.get(index);
    }
    
    /**
     * Sets the element at the specified index.
     */
    public void set(int index, JSONValue value) {
        elements.set(index, value);
    }
    
    /**
     * Removes the element at the specified index.
     */
    public JSONValue remove(int index) {
        return elements.remove(index);
    }
    
    /**
     * Returns the number of elements in this array.
     */
    public int size() {
        return elements.size();
    }
    
    /**
     * Checks if this array is empty.
     */
    public boolean isEmpty() {
        return elements.isEmpty();
    }
    
    /**
     * Checks if this array contains the specified element.
     */
    public boolean contains(JSONValue value) {
        return elements.contains(value);
    }
    
    @Override
    public JSONType getType() {
        return JSONType.ARRAY;
    }
    
    @Override
    public String toJSONString() {
        if (elements.isEmpty()) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        
        for (int i = 0; i < elements.size(); i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(elements.get(i).toJSONString());
        }
        
        sb.append("]");
        return sb.toString();
    }
    
    @Override
    public Iterator<JSONValue> iterator() {
        return elements.iterator();
    }
    
    @Override
    public String toString() {
        return toJSONString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        JSONArray jsonArray = (JSONArray) obj;
        return elements.equals(jsonArray.elements);
    }
    
    @Override
    public int hashCode() {
        return elements.hashCode();
    }
}