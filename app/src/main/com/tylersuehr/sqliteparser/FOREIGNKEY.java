package com.tylersuehr.sqliteparser;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright Tyler Suehr 2016
 * Created by tyler
 *
 * Annotates a SQLite FOREIGN KEY constraint. (NOT IMPLEMENTED YET)
 *
 * NOTE: It is important to note that all primary keys will be named, "id".
 *       so this annotation will only get the class for the foreign key because
 *       we know what the primary key field is: "id".
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FOREIGNKEY {
    Class<?> fkDataType();
}