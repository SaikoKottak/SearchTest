package com.nautrainee.search;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchTreeTest {

    public static final ClassFile CLASS_FILE = new ClassFile("one", 3);
    public static final ClassFile CLASS_FILE2 = new ClassFile("a", 2);
    public static final ClassFile CLASS_FILE3 = new ClassFile("three", 2);

    @Test
    public void add() throws Exception {
        SearchTree tree = makeSearchTree();
        assertTrue(tree.getClasses().isEmpty());
        assertEquals(3, tree.getChildren().keySet().size());
        assertTrue(tree.getChildren().containsKey('o'));
        SearchTree child = tree.getChildren().get('a');
        assertTrue(child.getChildren().isEmpty());
        assertEquals(1, tree.getChildren().get('o').getChildren().keySet().size());
        assertTrue(tree.getChildren().get('o').getChildren().keySet().contains('n'));
    }

    private SearchTree makeSearchTree() {
        SearchTree tree = new SearchTree();
        tree.add(CLASS_FILE);
        tree.add(CLASS_FILE2);
        tree.add(CLASS_FILE3);
        return tree;
    }

    @Test
    public void fill() throws Exception {
        SearchTree tree = makeSearchTree();
        tree.fill();
        assertEquals(3, tree.getClasses().size());
        assertEquals(CLASS_FILE, tree.getClasses().get(0));
        assertEquals(Collections.singletonList(CLASS_FILE), tree.getChildren().get('o').getChildren().get('n').getChildren().get('e').getClasses());
    }

    @Test
    public void merge() throws Exception {
        SearchTree tree = makeSearchTree();
        List<ClassFile> files = tree.merge(Arrays.asList(CLASS_FILE2, CLASS_FILE3), Collections.singletonList(CLASS_FILE));
        assertEquals(3, files.size());
    }

}