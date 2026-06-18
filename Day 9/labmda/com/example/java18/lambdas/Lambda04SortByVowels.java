package com.example.java18.lambdas;

import java.util.*;

public class Lambda04SortByVowels {

    private static long countVowels(String s) {
        return s.toLowerCase()
                .chars()
                .filter(ch -> "aeiou".indexOf(ch) >= 0)
                .count();
    }

    public static void main(String[] args) {

        List<String> words = new ArrayList<>(
                List.of("java", "stream", "aeiou", "code"));

        words.sort(
                (a, b) ->
                        Long.compare(countVowels(a), countVowels(b)));

        System.out.println(words);
    }
}