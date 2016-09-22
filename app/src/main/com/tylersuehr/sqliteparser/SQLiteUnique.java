package com.tylersuehr.sqliteparser;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright Tyler Suehr 2016
 * Created by tyler
 *
 * This annotates a UNIQUE SQL constraint.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SQLiteUnique {}