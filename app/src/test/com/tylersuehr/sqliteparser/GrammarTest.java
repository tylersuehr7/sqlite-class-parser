package com.tylersuehr.sqliteparser;
import static org.junit.Assert.*;

import com.tylersuehr.sqliteparser.Grammar;
import org.junit.Test;

/**
 * Copyright Tyler Suehr 2016
 * Created by tyler
 */
public class GrammarTest {
    @Test
    public void testToPlural() {
        assertEquals("cats", Grammar.plural("cat"));
        assertEquals("boxes", Grammar.plural("box"));
    }
}