package com.nautrainee.search;

public class Main {

    public static final int N = 10;

    public static void main(String[] args) {
        long[] dates = new long[N];
        String[] names = new String[]{"a", "b", "c", "ab", "abc", "cab", "bac", "eof", "fos", "aab"};
        for (int i = 0; i < N; i++) {
            dates[i] = i;
        }
        Searcher searcher = new Searcher();
        searcher.refresh(names, dates);
        String[] result = searcher.guess("b");
        for(String word : result) {
            System.out.println(word);
        }
    }

    private static String iToString(int i) {
        return null;
    }

    public static char[] makeAlphabet() {
        char[] chars = new char[62];
        for (int i = 0; i < 26; i++) {
            chars[i] = (char) ('a' + i);
        }
        for (int i = 0; i < 26; i++) {
            chars[i + 26] = (char) ('A' + i);
        }
        for (int i = 0; i < 10; i++) {
            chars[i + 52] = (char) ('1' + i);
        }
        return chars;
    }
}