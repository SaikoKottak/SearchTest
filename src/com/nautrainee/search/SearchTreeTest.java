package com.nautrainee.search;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class SearchTreeTest {

    public static final ClassFile CLASS_FILE = new ClassFile("a", 1);

    @Test
    public void add() throws Exception {
        SearchTree tree = makeSearchTree();
        assertTrue(tree.getClasses().isEmpty());
        assertEquals(1, tree.getChildren().keySet().size());
        assertTrue(tree.getChildren().containsKey('a'));
        SearchTree child = tree.getChildren().get('a');
        assertTrue(child.getChildren().isEmpty());
        assertEquals(Collections.singletonList(CLASS_FILE), child.getClasses());
    }

    private SearchTree makeSearchTree() {
        SearchTree tree = new SearchTree();
        tree.add(CLASS_FILE);
        return tree;
    }

    @Test
    public void fill() throws Exception {
        SearchTree tree = makeSearchTree();
        tree.fill();
        assertEquals(1,tree.getClasses().size());
        assertEquals(CLASS_FILE, tree.getClasses().get(0));
        assertEquals(Collections.singletonList(CLASS_FILE), tree.getChildren().get('a').getClasses());
    }

}