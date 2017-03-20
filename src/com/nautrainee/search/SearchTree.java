package com.nautrainee.search;

import java.util.*;
import java.util.stream.Collectors;

public class SearchTree {

    private List<ClassFile> classes = new ArrayList<>();
    private Map<Character, SearchTree> children = new HashMap<>();

    public List<ClassFile> getClasses() {
        return classes;
    }

    public Map<Character, SearchTree> getChildren() {
        return children;
    }

    public void add(ClassFile classFile) {
        add(classFile.className, classFile);
    }

    private void add(String suffix, ClassFile classFile) {
        if (suffix == null || suffix.isEmpty()) {
            classes.add(classFile);
            return;
        }

        Character letter = suffix.charAt(0);
        String tail = suffix.substring(1, suffix.length());
        if (!children.containsKey(letter)) {
            children.put(letter, new SearchTree());
        }
        SearchTree child = children.get(letter);
        child.add(tail, classFile);
    }

    public void fill() {
        if (children.isEmpty()) {
            Collections.sort(classes);
            return;
        }

        children.values().forEach(SearchTree::fill);
        classes = merge(children.values().stream()
                .map(SearchTree::getClasses)
                .collect(Collectors.toList()));
    }

    private List<ClassFile> merge(Collection<List<ClassFile>> lists) {
        Iterator<List<ClassFile>> iter = lists.iterator();
        if (!iter.hasNext()) {
            return Collections.emptyList();
        }
        List<ClassFile> first = iter.next();
        List<List<ClassFile>> res = new ArrayList<>(lists.size() / 2 + 1);
        if (!iter.hasNext()) {
            return first;
        }
        List<ClassFile> second = iter.next();
        res.add(merge(first, second));

        while (iter.hasNext()) {
            first = iter.next();
            if (!iter.hasNext()) {
                res.add(first);
                break;
            }
            second = iter.next();
            res.add(merge(first, second));
        }

        return merge(res);
    }

    protected List<ClassFile> merge(List<ClassFile> first, List<ClassFile> second) {
        List<ClassFile> res = new ArrayList<>(first.size() + second.size());
        Iterator<ClassFile> firstIterator = first.iterator();
        Iterator<ClassFile> secondIterator = second.iterator();
        if (!firstIterator.hasNext()) {
            return addAll(res, secondIterator);
        }
        if (!secondIterator.hasNext()) {
            return addAll(res, firstIterator);
        }
        ClassFile firstValue = firstIterator.next();
        ClassFile secondValue = secondIterator.next();
        while (true) {
            if (firstValue.compareTo(secondValue) <= 0) {
                res.add(firstValue);
                if (firstIterator.hasNext()) {
                    firstValue = firstIterator.next();
                } else {
                    res.add(secondValue);
                    return addAll(res, secondIterator);
                }
            } else {
                res.add(secondValue);
                if (secondIterator.hasNext()) {
                    secondValue = secondIterator.next();
                } else {
                    res.add(firstValue);
                    return addAll(res, firstIterator);
                }
            }
        }
    }

    private List<ClassFile> addAll(List<ClassFile> res, Iterator<ClassFile> iterator) {
        while (iterator.hasNext()) {
            res.add(iterator.next());
        }
        return res;
    }

    public List<ClassFile> get(String name) {
        if (name == null || name.isEmpty()) {
            return classes;
        }
        Character letter = name.charAt(0);
        String tail = name.substring(1, name.length());
        SearchTree child = children.get(letter);
        if (child == null) {
            return Collections.emptyList();
        }
        return child.get(tail);
    }
}