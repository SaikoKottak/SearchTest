package com.nautrainee.search;

import com.nautrainee.search.Searcher;
import org.junit.Test;

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

}