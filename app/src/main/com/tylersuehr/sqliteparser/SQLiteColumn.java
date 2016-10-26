package com.tylersuehr.sqliteparser;

/**
 * Copyright Tyler Suehr 2016
 * Created by tyler
 *
 * This represents a single column in the SQLite database table.
 */
final class SQLiteColumn {
    private boolean primaryKey = false;
    private boolean unique = false;
    private boolean notNull = false;
    private String dataType;
    private String name;


    SQLiteColumn(String name, String dataType) {
        this.name = name;
        this.dataType = dataType;
    }

    String getDataType() {
        return dataType;
    }

    String getName() {
        return name;
    }

    boolean isPrimaryKey() {
        return primaryKey;
    }

    void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    boolean isUnique() {
        return unique;
    }

    void setUnique(boolean unique) {
        this.unique = unique;
    }

    boolean isNotNull() {
        return notNull;
    }

    void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }
}