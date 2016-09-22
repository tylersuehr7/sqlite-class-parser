package com.tylersuehr.sqliteparser;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Copyright Tyler Suehr 2016
 * Created by tyler
 */
public class SQLiteParserTest {
    @Test
    public void testParse() {
        final String SQL = "CREATE TABLE [testcaseobjects] ([id] INTEGER PRIMARY KEY,[name] TEXT,[email] TEXT);";

        SQLiteParser parser = SQLiteParser.getInstance();
        parser.clearFlags();

        String parsed = parser.parse(TestCaseObject.class);

        assertEquals(SQL, parsed);
    }

    @Test
    public void testTableLowerParse() {
        final String SQL = "CREATE TABLE [TestCaseObjects] ([id] INTEGER PRIMARY KEY,[name] TEXT,[email] TEXT);";

        SQLiteParser parser = SQLiteParser.getInstance();
        parser.clearFlags();
        parser.tableLowercase = false;

        String parsed = parser.parse(TestCaseObject.class);

        assertEquals(SQL, parsed);
    }

    @Test
    public void testColUppercaseParse() {
        final String SQL = "CREATE TABLE [testcaseobjects] ([Id] INTEGER PRIMARY KEY,[Name] TEXT,[Email] TEXT);";

        SQLiteParser parser = SQLiteParser.getInstance();
        parser.clearFlags();
        parser.colFirstUppercase = true;

        String parsed = parser.parse(TestCaseObject.class);

        assertEquals(SQL, parsed);
    }

    @Test
    public void testNoTablePluralParse() {
        final String SQL = "CREATE TABLE [testcaseobject] ([id] INTEGER PRIMARY KEY,[name] TEXT,[email] TEXT);";

        SQLiteParser parser = SQLiteParser.getInstance();
        parser.clearFlags();
        parser.tablePlural = false;

        String parsed = parser.parse(TestCaseObject.class);

        assertEquals(SQL, parsed);
    }

    @Test
    public void testNoFilterParse() {
        final String SQL = "CREATE TABLE [testcaseobjects] ([id] INTEGER PRIMARY KEY,[name] TEXT,[email] TEXT,[money] REAL);";

        SQLiteParser parser = SQLiteParser.getInstance();
        parser.clearFlags();
        parser.filter = false;

        String parsed = parser.parse(TestCaseObject.class);

        assertEquals(SQL, parsed);
    }
}