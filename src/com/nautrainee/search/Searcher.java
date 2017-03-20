package com.nautrainee.search;

import java.util.List;
import java.util.stream.Collectors;

public class Searcher implements ISearcher {
    SearchTree tree;

    @Override
    public void refresh(String[] classNames, long[] modificationDates) {
        if (classNames.length != modificationDates.length) {
            throw new IllegalArgumentException("Length of arrays isn't equals.");
        }
        tree = new SearchTree();
        for (int i = 0; i < classNames.length; i++) {
            tree.add(new ClassFile(classNames[i], modificationDates[i]));
        }
        tree.fill();
    }

    @Override
    public String[] guess(String start) {
        List<ClassFile> classFiles = tree.get(start);
        return classFiles.stream()
                .map(ClassFile::getClassName)
                .limit(12)
                .collect(Collectors.toList())
                .toArray(new String[0]);
    }

}