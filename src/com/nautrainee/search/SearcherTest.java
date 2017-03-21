package com.nautrainee.search;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearcherTest {

    @Test
    public void refreshEmpty() throws Exception {
        new Searcher().refresh(new String[]{}, new long[]{});
    }

    @Test
    public void refreshOne() throws Exception {
        new Searcher().refresh(new String[]{"asdf"}, new long[]{1});
    }

    @Test
    public void refreshMany() throws Exception {
        new Searcher().refresh(new String[]{"asdf", "q", "a"}, new long[]{1, 2, 3});
    }

    @Test
    public void guess() throws Exception {
        Searcher searcher = new Searcher();
        searcher.refresh(new String[]{"one", "two", "three", "four"}, new long[]{1, 1, 3, 2});
        assertTrue(searcher.guess("on").length == 1);
        assertEquals("two", searcher.guess("t")[1]);
    }

}