package com.tylersuehr.sqliteparser;
import java.util.Collection;

/**
 * Copyright Tyler Suehr 2016
 * Created by tyler
 *
 * This is what we will use to parse a class and create a SQL table
 * creation statement.
 *
 * <p>
 *     Example usage 1:
 *     -----------------
 *     public static void main(String[] args) {
 *          SQLiteParser parser = SQLiteParser.getInstance();
 *          parser.setColFirstUppercase(false);
 *          parser.setTableLowercase(true);
 *          parser.setTablePlural(true);
 *          parser.setFilter(true);
 *
 *          String table = parser.parse(MyEntityObject.class);
 *     }
 * </p>
 *
 * <p>
 *     Example usage 2:
 *     -----------------
 *     public static void main(String[] args) {
 *          String table = SQLiteParser.getInstance()
 *              .setColFirstUppercase(false)
 *              .setTableLowercase(true)
 *              .setTablePlural(true)
 *              .setFilter(true)
 *              .parse(MyEntityObject.class);
 *     }
 * </p>
 */
public final class SQLiteParser {
    private static volatile SQLiteParser instance;
    private final SQLiteMapper mapper = new SQLiteMapper();
    boolean colFirstUppercase = false;
    boolean tableLowercase = true;
    boolean tablePlural = true;
    boolean filter = true;


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
     * Parses a class to create a SQLite SQL table creation statement.
     * @param type Class
     * @return SQLite SQL table creation statement
     */
    public String parse(Class<?> type) {
        // Get table name
        String className = tablePlural ? Grammar.plural(type.getSimpleName()) : type.getSimpleName();
        className = tableLowercase ? className.toLowerCase() : className;

        // Get table columns
        Collection<SQLiteColumn> cols = filter ? mapper.mapFields(type) : mapper.mapAllFields(type);
        mapper.validateColumns();

        // Create table creation statement
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE [").append(className).append("] (");

        // Create table columns
        boolean few = false;
        for (SQLiteColumn col : cols) {
            sb.append(few ? "," : "").append("[");

            sb.append(colFirstUppercase ? firstUppercase(col.getName()) : col.getName());
            sb.append("] ").append(col.getDataType());
            sb.append(col.isPrimary() ? " PRIMARY KEY" : "");
            sb.append(col.isUnique() ? " UNIQUE" : "");
            sb.append(col.isNotNull() ? " NOT NULL" : "");

            few = true;
        }

        sb.append(");");
        return sb.toString();
    }

    /**
     * Sets flag to make the table name all lowercase.
     * @param value True if lowercase
     */
    public SQLiteParser setTableLowercase(boolean value) {
        this.tableLowercase = value;
        return this;
    }

    /**
     * Sets a flag to pluralize the table name.
     * @param value True if pluralized
     */
    public SQLiteParser setTablePlural(boolean value) {
        this.tablePlural = value;
        return this;
    }

    /**
     * Sets a flag to capitalize the first letter in column names.
     * @param value True if first letter capitalized
     */
    public SQLiteParser setColFirstUppercase(boolean value) {
        this.colFirstUppercase = value;
        return this;
    }

    /**
     * Sets a flag to filter fields with the {@link SQLiteIgnore} annotation.
     * @param value True if should filter
     */
    public SQLiteParser setFilter(boolean value) {
        this.filter = value;
        return this;
    }

    /**
     * Resets all flags to their default values.
     */
    public SQLiteParser clearFlags() {
        this.colFirstUppercase = false;
        this.tableLowercase = true;
        this.tablePlural = true;
        this.filter = true;
        return this;
    }

    private String firstUppercase(String text) {
        char first = Character.toUpperCase(text.charAt(0));
        return (first + text.substring(1, text.length()));
    }
}