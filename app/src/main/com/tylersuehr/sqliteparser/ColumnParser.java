package com.tylersuehr.sqliteparser;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Copyright Tyler Suehr 2016
 * Created by tyler
 *
 * This utility will handle create SQLiteColumns from class fields and mapping them
 * to their corresponding collection.
 */
final class ColumnParser {
    private final Collection<SQLiteColumn> foreignKeys = new LinkedList<>();
    private final Collection<SQLiteColumn> fields = new LinkedList<>();
    private final DataTypeParser parser = new DataTypeParser();
    private boolean hasKey = false;


    ColumnParser() {}

    /**
     * Parse class fields into their corresponding collection, but ignore
     * fields with the {@link IGNORE} annotation.
     * @param type Class
     */
    void parse(Class<?> type) {
        // Get all the fields from class
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            // Only add fields without the IGNORE annotation
            if (field.getAnnotation(IGNORE.class) == null) {
                assignField(field);
            }
        }

        // Check for super class that isn't an Object (recursive)
        Class<?> superClass = type.getSuperclass();
        if (!superClass.isAssignableFrom(Object.class)) {
            parse(superClass);
        }

        // Validate data
        if (!hasKey) {
            throw new IllegalStateException("No primary key determined!");
        }
    }

    /**
     * Parse all class fields into their corresponding collection.
     * @param type Class
     */
    void parseAll(Class<?> type) {
        // Get all the fields from class
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            assignField(field);
        }

        // Check for super class that isn't an Object (recursive)
        Class<?> superClass = type.getSuperclass();
        if (!superClass.isAssignableFrom(Object.class)) {
            parse(superClass);
        }

        // Validate data
        if (!hasKey) {
            throw new IllegalStateException("No primary key determined!");
        }
    }

    Collection<SQLiteColumn> getFields() {
        return fields;
    }

    Collection<SQLiteColumn> getForeignKeys() {
        return foreignKeys;
    }

    /**
     * Create a {@link SQLiteColumn} for the given field and adds it to the
     * appropriate corresponding collection.
     * @param f {@link Field}
     */
    private void assignField(Field f) {
        // Check for FK
        if (f.getAnnotation(FOREIGNKEY.class) != null) {
            FOREIGNKEY fk = f.getAnnotation(FOREIGNKEY.class);
            final SQLiteColumn col = new SQLiteColumn(f.getName(), parser.getDataType(fk.fkDataType()));

            foreignKeys.add(col);
            return;
        }

        // At this point it's a normal field
        final SQLiteColumn col = new SQLiteColumn(f.getName(), parser.getDataType(f.getType()));

        // Check for PRIMARY KEY constraint
        if (f.getName().equalsIgnoreCase("id")) {
            hasKey = true;
            col.setPrimaryKey(true);
        }

        // Check for UNIQUE constraint
        col.setUnique(f.getAnnotation(UNIQUE.class) != null);

        // Check for NOT NULL constraint
        col.setNotNull(f.getAnnotation(NOTNULL.class) != null);

        fields.add(col);
    }
}