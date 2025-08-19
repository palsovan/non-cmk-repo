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
 * Represents a JSON boolean value.
 */
public class JSONBoolean implements JSONValue {
    
    public static final JSONBoolean TRUE = new JSONBoolean(true);
    public static final JSONBoolean FALSE = new JSONBoolean(false);
    
    private final boolean value;
    
    private JSONBoolean(boolean value) {
        this.value = value;
    }
    
    /**
     * Returns a JSONBoolean instance for the given boolean value.
     */
    public static JSONBoolean valueOf(boolean value) {
        return value ? TRUE : FALSE;
    }
    
    /**
     * Returns the boolean value.
     */
    public boolean getValue() {
        return value;
    }
    
    @Override
    public JSONType getType() {
        return JSONType.BOOLEAN;
    }
    
    @Override
    public String toJSONString() {
        return Boolean.toString(value);
    }
    
    @Override
    public String toString() {
        return Boolean.toString(value);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        JSONBoolean that = (JSONBoolean) obj;
        return value == that.value;
    }
    
    @Override
    public int hashCode() {
        return Boolean.hashCode(value);
    }
}