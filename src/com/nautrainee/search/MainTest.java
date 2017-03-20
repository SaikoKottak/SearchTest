package com.nautrainee.search;

import org.junit.Test;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void makeAlphabet(){
        char[] chars = Main.makeAlphabet();
        System.out.println(Arrays.asList(chars).stream()
                .map(Object::toString)
                .collect(Collectors.joining(",")));
    }

}