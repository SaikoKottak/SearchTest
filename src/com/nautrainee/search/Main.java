package com.nautrainee.search;

public class Main {

    public static final int N = 100_000;

    public static void main(String[] args) {
        long[] dates = new long[N];
        String[] names = new String[N];

        for (int i = 0; i < N; i++) {
            dates[i] = i;
            names[i] = makeString(32);
        }
        Searcher searcher = new Searcher();
        long startTime = System.currentTimeMillis();
        searcher.refresh(names, dates);
        long refreshTime = System.currentTimeMillis();
        String[] result = searcher.guess("");
        for (String className : result) {
            System.out.println(className);
        }
        long guessTime = System.currentTimeMillis();

        System.out.println("Refresh time is " + (refreshTime - startTime));
        System.out.println("Guess time is " + (guessTime - refreshTime));
    }

    public static String makeString(int maxLength) {
        String symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randString = new StringBuilder();
        int count = (int) (Math.random() * maxLength);
        for (int i = 0; i < count; i++)
            randString.append(symbols.charAt((int) (Math.random() * symbols.length())));

        return randString.toString();
    }

}