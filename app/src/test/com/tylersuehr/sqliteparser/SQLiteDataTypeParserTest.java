package com.tylersuehr.sqliteparser;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.UUID;

/**
 * Copyright Tyler Suehr 2016
 * Created by tyler
 */
public class SQLiteDataTypeParserTest {
    @Test
    public void testTextDataType() {
        Class<?>[] types = new Class<?>[] {
                CharSequence.class, String.class, UUID.class,
                Character.class, char.class
        };

        SQLiteDataTypeParser dataParser = new SQLiteDataTypeParser();
        for (Class<?> type : types) {
            assertEquals("TEXT", dataParser.getDataType(type));
        }
    }

    @Test
    public void testIntegerDataType() {
        Class<?>[] types = new Class<?>[] {
                Integer.class, Long.class, Boolean.class, int.class,
                long.class, boolean.class
        };

        SQLiteDataTypeParser dataParser = new SQLiteDataTypeParser();
        for (Class<?> type : types) {
            assertEquals("INTEGER", dataParser.getDataType(type));
        }
    }

    @Test
    public void testRealDataType() {
        Class<?>[] types = new Class<?>[] {
                Float.class, Double.class, Byte.class, Byte[].class,
                float.class, double.class, byte.class, byte[].class
        };

        SQLiteDataTypeParser dataParser = new SQLiteDataTypeParser();
        for (Class<?> type : types) {
            assertEquals("REAL", dataParser.getDataType(type));
        }
    }
}