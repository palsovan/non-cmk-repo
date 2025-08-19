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

import java.math.BigDecimal;

/**
 * Represents a JSON number value.
 */
public class JSONNumber implements JSONValue {
    
    private final Number value;
    
    public JSONNumber(Number value) {
        if (value == null) {
            throw new IllegalArgumentException("Number value cannot be null");
        }
        this.value = value;
    }
    
    public JSONNumber(String numberString) throws NumberFormatException {
        if (numberString == null || numberString.trim().isEmpty()) {
            throw new IllegalArgumentException("Number string cannot be null or empty");
        }
        
        try {
            // Try to parse as integer first
            if (!numberString.contains(".") && !numberString.toLowerCase().contains("e")) {
                this.value = Long.parseLong(numberString);
            } else {
                // Parse as decimal
                this.value = new BigDecimal(numberString);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid number format: " + numberString);
        }
    }
    
    /**
     * Returns the numeric value.
     */
    public Number getValue() {
        return value;
    }
    
    /**
     * Returns the value as an integer.
     */
    public int intValue() {
        return value.intValue();
    }
    
    /**
     * Returns the value as a long.
     */
    public long longValue() {
        return value.longValue();
    }
    
    /**
     * Returns the value as a double.
     */
    public double doubleValue() {
        return value.doubleValue();
    }
    
    /**
     * Returns the value as a float.
     */
    public float floatValue() {
        return value.floatValue();
    }
    
    /**
     * Checks if this number is an integer (no decimal part).
     */
    public boolean isInteger() {
        if (value instanceof Long || value instanceof Integer) {
            return true;
        }
        if (value instanceof BigDecimal) {
            BigDecimal bd = (BigDecimal) value;
            return bd.stripTrailingZeros().scale() <= 0;
        }
        return value.doubleValue() == Math.floor(value.doubleValue());
    }
    
    @Override
    public JSONType getType() {
        return JSONType.NUMBER;
    }
    
    @Override
    public String toJSONString() {
        if (value instanceof BigDecimal) {
            return ((BigDecimal) value).toPlainString();
        }
        return value.toString();
    }
    
    @Override
    public String toString() {
        return toJSONString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        JSONNumber that = (JSONNumber) obj;
        
        // Compare numeric values, handling different number types
        if (value instanceof BigDecimal && that.value instanceof BigDecimal) {
            return ((BigDecimal) value).compareTo((BigDecimal) that.value) == 0;
        }
        
        return Double.compare(value.doubleValue(), that.value.doubleValue()) == 0;
    }
    
    @Override
    public int hashCode() {
        return Double.hashCode(value.doubleValue());
    }
}