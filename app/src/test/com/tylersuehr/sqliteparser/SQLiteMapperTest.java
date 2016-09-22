package com.tylersuehr.sqliteparser;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collection;
import static org.junit.Assert.*;

/**
 * Copyright Tyler Suehr 2016
 * Created by tyler
 */
public class SQLiteMapperTest {
    @Test
    public void mapAllFields() throws Exception {
        Collection<SQLiteColumn> result = createAllTestColumns();

        SQLiteMapper mapper = new SQLiteMapper();
        Collection<SQLiteColumn> value = mapper.mapAllFields(TestCaseObject.class);

        assertEquals(result.size(), value.size());
    }

    @Test
    public void mapFields() throws Exception {
        Collection<SQLiteColumn> result = createTestColumns();

        SQLiteMapper mapper = new SQLiteMapper();
        Collection<SQLiteColumn> value = mapper.mapFields(TestCaseObject.class);

        assertEquals(result.size(), value.size());
    }

    private Collection<SQLiteColumn> createTestColumns() {
        Collection<SQLiteColumn> cols = new ArrayList<>();
        cols.add(createCol("id", "INTEGER", true, false, false));
        cols.add(createCol("name", "TEXT", false, false, false));
        cols.add(createCol("email", "TEXT", false, false, false));
        return cols;
    }
    private Collection<SQLiteColumn> createAllTestColumns() {
        Collection<SQLiteColumn> cols = new ArrayList<>();
        cols.add(createCol("id", "INTEGER", true, false, false));
        cols.add(createCol("name", "TEXT", false, false, false));
        cols.add(createCol("email", "TEXT", false, false, false));
        cols.add(createCol("money", "REAL", false, false, false));
        return cols;
    }

    private SQLiteColumn createCol(String name, String dt, boolean pk, boolean nn, boolean un) {
        SQLiteColumn col = new SQLiteColumn(name, dt);
        col.setPrimary(pk);
        col.setUnique(un);
        col.setNotNull(nn);
        return col;
    }
}