package com.tylersuehr.sqliteparser;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright Tyler Suehr 2016
 * Created by tyler
 *
 * This annotation will prevent the parser from reading
 * the field it is annotating.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SQLiteIgnore {}