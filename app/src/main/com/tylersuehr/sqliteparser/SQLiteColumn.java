package com.tylersuehr.sqliteparser;

/**
 * Copyright Tyler Suehr 2016
 * Created by tyler
 *
 * This models a column in SQLite.
 */
final class SQLiteColumn {
    private String name;
    private String dataType;
    private Class<?> foreignKeyClass;
    private boolean notNull = false;
    private boolean primary = false;
    private boolean unique = false;
    private boolean hasForeignKey = false;


    SQLiteColumn() {}

    SQLiteColumn(String name, String dataType) {
        this.name = name;
        this.dataType = dataType;
    }

    Class<?> getForeignKeyClass() {
        return foreignKeyClass;
    }

    void setForeignKeyClass(Class<?> foreignKeyClass) {
        this.foreignKeyClass = foreignKeyClass;
    }

    boolean hasForeignKey() {
        return hasForeignKey;
    }

    void setHasForeignKey(boolean hasForeignKey) {
        this.hasForeignKey = hasForeignKey;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getDataType() {
        return dataType;
    }

    void setDataType(String dataType) {
        this.dataType = dataType;
    }

    boolean isNotNull() {
        return notNull;
    }

    void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    boolean isPrimary() {
        return primary;
    }

    void setPrimary(boolean primary) {
        this.primary = primary;
    }

    boolean isUnique() {
        return unique;
    }

    void setUnique(boolean unique) {
        this.unique = unique;
    }
}