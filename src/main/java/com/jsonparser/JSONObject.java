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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents a JSON object.
 */
public class JSONObject implements JSONValue {
    
    private final Map<String, JSONValue> properties;
    
    public JSONObject() {
        this.properties = new HashMap<>();
    }
    
    public JSONObject(Map<String, JSONValue> properties) {
        this.properties = new HashMap<>(properties);
    }
    
    /**
     * Puts a key-value pair into this JSON object.
     */
    public void put(String key, JSONValue value) {
        properties.put(key, value);
    }
    
    /**
     * Gets the value associated with the given key.
     */
    public JSONValue get(String key) {
        return properties.get(key);
    }
    
    /**
     * Checks if this object contains the given key.
     */
    public boolean containsKey(String key) {
        return properties.containsKey(key);
    }
    
    /**
     * Returns the set of keys in this object.
     */
    public Set<String> keySet() {
        return properties.keySet();
    }
    
    /**
     * Returns the number of key-value pairs in this object.
     */
    public int size() {
        return properties.size();
    }
    
    /**
     * Checks if this object is empty.
     */
    public boolean isEmpty() {
        return properties.isEmpty();
    }
    
    /**
     * Removes the key-value pair with the given key.
     */
    public JSONValue remove(String key) {
        return properties.remove(key);
    }
    
    @Override
    public JSONType getType() {
        return JSONType.OBJECT;
    }
    
    @Override
    public String toJSONString() {
        if (properties.isEmpty()) {
            return "{}";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        boolean first = true;
        for (Map.Entry<String, JSONValue> entry : properties.entrySet()) {
            if (!first) {
                sb.append(",");
            }
            first = false;
            
            sb.append("\"").append(escapeString(entry.getKey())).append("\":");
            sb.append(entry.getValue().toJSONString());
        }
        
        sb.append("}");
        return sb.toString();
    }
    
    private String escapeString(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            switch (c) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    if (c < 32) {
                        sb.append(String.format("\\u%04x", (int) c));
                    } else {
                        sb.append(c);
                    }
                    break;
            }
        }
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return toJSONString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        JSONObject that = (JSONObject) obj;
        return properties.equals(that.properties);
    }
    
    @Override
    public int hashCode() {
        return properties.hashCode();
    }
}