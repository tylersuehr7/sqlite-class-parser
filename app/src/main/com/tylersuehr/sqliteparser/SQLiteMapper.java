package com.tylersuehr.sqliteparser;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Copyright Tyler Suehr 2016
 * Created by tyler
 *
 * This maps all the fields of a given class to a {@link SQLiteColumn} object.
 */
final class SQLiteMapper {
    private final SQLiteDataTypeParser parser = new SQLiteDataTypeParser();
//    private SQLiteColumn primaryKey = null;
//    private boolean hasForeignKey = false;
    private boolean hasPrimary = false;


    SQLiteMapper() {}

    /**
     * Maps all the fields of a class to a respective {@link SQLiteColumn}.
     * @param type Class
     * @return Collection of {@link SQLiteColumn}
     */
    Collection<SQLiteColumn> mapAllFields(Class<?> type) {
        Collection<SQLiteColumn> cols = new ArrayList<>();

        // Create columns for each field and add them to our collection
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            cols.add(createColumn(field));
        }

        // Check if our class has a super class that isn't object
        // If it does, recursively call this method
        Class<?> superClass = type.getSuperclass();
        if (!superClass.isAssignableFrom(Object.class)) {
            cols.addAll(mapAllFields(superClass));
        }

        return cols;
    }

    /**
     * Maps all the filtered fields (without the SQLiteIgnore annotation) to a
     * respective {@link SQLiteColumn}.
     * @param type Class
     * @return Collection of {@link SQLiteColumn}
     */
    Collection<SQLiteColumn> mapFields(Class<?> type) {
        Collection<SQLiteColumn> cols = new ArrayList<>();

        // Create columns for each field and add them to our collection
        // A field is valid if it doesn't have the SQLiteIgnore annotation
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(SQLiteIgnore.class) == null) {
                cols.add(createColumn(field));
            }
        }

        // Check if our class has a super class that isn't object
        // If it does, recursively call this method
        Class<?> superClass = type.getSuperclass();
        if (!superClass.isAssignableFrom(Object.class)) {
            cols.addAll(mapFields(superClass));
        }

        return cols;
    }

    /**
     * Creates a {@link SQLiteColumn} from a given field.
     * @param f Field
     * @return {@link SQLiteColumn}
     */
    private SQLiteColumn createColumn(Field f) {
        SQLiteColumn col = new SQLiteColumn();
        col.setName(f.getName());
        col.setDataType(parser.getDataType(f.getType()));

        // Make sure we don't have multiple primary keys
        boolean primary = f.getName().equalsIgnoreCase("id");
        if (primary && hasPrimary) {
            throw new IllegalStateException("Cannot have multiple primary keys! (Primary keys contain 'id' as its name)");
        } else if (primary) {
            hasPrimary = true;
        }

        col.setPrimary(primary);
        col.setNotNull(f.getAnnotation(SQLiteNotNull.class) != null);
        col.setUnique(f.getAnnotation(SQLiteUnique.class) != null);

        // Check for foreign keys
        if (f.getAnnotation(SQLiteForeignKey.class) != null) {
            SQLiteForeignKey fk = f.getAnnotation(SQLiteForeignKey.class);
            col.setHasForeignKey(true);
            col.setForeignKeyClass(fk.references());
        }

        return col;
    }

    /**
     * Validates the overall column collection.
     */
    void validateColumns() {
        if (!hasPrimary) {
            throw new IllegalStateException("No primary key detected! (Name a field 'id' to be parsed as primary key)");
        }

        // Here the validation is good so reset values
        hasPrimary = false;
    }
}