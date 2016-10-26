package com.tylersuehr.sqliteparser;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Copyright Tyler Suehr 2016
 * Created by tyler
 *
 * This will used to help us determine what SQLite data type a field should be.
 */
final class DataTypeParser {
    private static final String DATA_TYPE_TEXT = "TEXT";
    private static final String DATA_TYPE_INTEGER = "INTEGER";
    private static final String DATA_TYPE_REAL = "REAL";
    private final Map<Class<?>, String> dataTypes;


    DataTypeParser() {
        // We map all the Java data types with the corresponding
        // SQLite data type using a HashMap
        this.dataTypes = new HashMap<>();
        this.dataTypes.put(CharSequence.class, DATA_TYPE_TEXT);
        this.dataTypes.put(String.class, DATA_TYPE_TEXT);
        this.dataTypes.put(UUID.class, DATA_TYPE_TEXT);
        this.dataTypes.put(Character.class, DATA_TYPE_TEXT);
        this.dataTypes.put(char.class, DATA_TYPE_TEXT);

        this.dataTypes.put(Integer.class, DATA_TYPE_INTEGER);
        this.dataTypes.put(Long.class, DATA_TYPE_INTEGER);
        this.dataTypes.put(Boolean.class, DATA_TYPE_INTEGER);
        this.dataTypes.put(int.class, DATA_TYPE_INTEGER);
        this.dataTypes.put(long.class, DATA_TYPE_INTEGER);
        this.dataTypes.put(boolean.class, DATA_TYPE_INTEGER);

        this.dataTypes.put(Float.class, DATA_TYPE_REAL);
        this.dataTypes.put(Double.class, DATA_TYPE_REAL);
        this.dataTypes.put(Byte.class, DATA_TYPE_REAL);
        this.dataTypes.put(Byte[].class, DATA_TYPE_REAL);
        this.dataTypes.put(float.class, DATA_TYPE_REAL);
        this.dataTypes.put(double.class, DATA_TYPE_REAL);
        this.dataTypes.put(byte.class, DATA_TYPE_REAL);
        this.dataTypes.put(byte[].class, DATA_TYPE_REAL);
    }

    /**
     * Gets a valid SQLite data type name; formatted in proper
     * SQL syntax.
     * @param type Object's class
     * @return SQLite data type as string value
     */
    String getDataType(Class<?> type) {
        if (dataTypes.containsKey(type)) {
            return dataTypes.get(type);
        }
        throw new IllegalArgumentException("Not a valid SQLite data type!");
    }

    /**
     * This uses autoboxing for getting the class of primitive data
     * type.
     * @param value Object
     * @return Object's class
     */
    Class<?> getPrimitiveClassType(Object value) {
        return value.getClass();
    }
}