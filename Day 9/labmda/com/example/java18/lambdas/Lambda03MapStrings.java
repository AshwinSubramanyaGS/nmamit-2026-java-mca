package com.example.java18.lambdas;

import java.util.*;
import java.util.function.Function;

public class Lambda03MapStrings {

    public static List<Integer> map(
            List<String> list,
            Function<String, Integer> mapper) {

        List<Integer> result = new ArrayList<>();

        for (String s : list) {
            result.add(mapper.apply(s));
        }

        return result;
    }

    public static void main(String[] args) {

        List<String> words = List.of("Java", "Lambda", "Stream");

        System.out.println(map(words, String::length));
    }
}