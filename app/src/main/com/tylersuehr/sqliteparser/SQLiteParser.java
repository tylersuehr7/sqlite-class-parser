package com.tylersuehr.sqliteparser;

/**
 * Copyright Tyler Suehr 2016
 * Created by tyler
 *
 * This utility will allow us to parse SQLite queries and statements easily.
 */
public final class SQLiteParser {
    private static volatile SQLiteParser instance;
    private final ColumnParser parser = new ColumnParser();
    private boolean colFirstUppercase = false;
    private boolean tableLowercase = true;
    private boolean tablePlural = true;
    private boolean filter = true;


    private SQLiteParser() {}
    public static SQLiteParser getInstance() {
        if (instance == null) {
            synchronized (SQLiteParser.class) {
                if (instance == null) {
                    instance = new SQLiteParser();
                }
            }
        }
        return instance;
    }

    /**
     * Creates a SQLite table creation statement given a class.
     * @param type Class
     * @return SQLite table creation statement
     */
    public String parseTable(Class<?> type) {
        // Get table name
        String tableName = tablePlural ? Grammar.plural(type.getSimpleName()) : type.getSimpleName();
        tableName = tableLowercase ? tableName.toLowerCase() : tableName;

        // Parse fields into SQLiteColumns
        if (filter)
            parser.parse(type);
        else
            parser.parseAll(type);

        // Start table creation statement
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE [").append(tableName).append("] (");

        // Create normal table columns (not foreign keys)
        boolean few = false;
        for (SQLiteColumn col : parser.getFields()) {
            sb.append(few ? ", " : "").append("[");

            sb.append(colFirstUppercase ? firstUppercase(col.getName()) : col.getName());
            sb.append("] ").append(col.getDataType());
            sb.append(col.isPrimaryKey() ? " PRIMARY KEY" : "");
            sb.append(col.isUnique() ? " UNIQUE" : "");
            sb.append(col.isNotNull() ? " NOT NULL" : "");

            few = true;
        }

        // Create foreign key table columns
        for (SQLiteColumn col : parser.getForeignKeys()) {
            sb.append(few ? ", " : "").append("[");

            String colName = col.getName() + "Id";
            String fkName = tablePlural ? Grammar.plural(col.getName()) : col.getName();
            fkName = tableLowercase ? fkName.toLowerCase() : fkName;
            String fkDataType = col.getDataType(); // Col data type is same as foreign key's
            String fkId = colFirstUppercase ? "Id" : "id";

            sb.append(colName).append("] ").append(fkDataType);
            sb.append(col.isUnique() ? " UNIQUE" : "");
            sb.append(col.isNotNull() ? " NOT NULL" : "");

            sb.append(", ");
            sb.append("FOREIGN KEY ([").append(colName).append("]) REFERENCES [");
            sb.append(fkName).append("]([");
            sb.append(fkId).append("])");
        }

        sb.append(");");
        return sb.toString();
    }

    public boolean isColFirstUppercase() {
        return colFirstUppercase;
    }

    public void setColFirstUppercase(boolean colFirstUppercase) {
        this.colFirstUppercase = colFirstUppercase;
    }

    public boolean isTableLowercase() {
        return tableLowercase;
    }

    public void setTableLowercase(boolean tableLowercase) {
        this.tableLowercase = tableLowercase;
    }

    public boolean isTablePlural() {
        return tablePlural;
    }

    public void setTablePlural(boolean tablePlural) {
        this.tablePlural = tablePlural;
    }

    public boolean isFilter() {
        return filter;
    }

    public void setFilter(boolean filter) {
        this.filter = filter;
    }

    /**
     * Makes the first character in the given text capitalized.
     * @param text Text
     * @return Formatted text
     */
    private String firstUppercase(String text) {
        char first = Character.toUpperCase(text.charAt(0));
        return (first + text.substring(1, text.length()));
    }
}