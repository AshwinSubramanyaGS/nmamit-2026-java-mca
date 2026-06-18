package com.example.java18.streams;

import java.util.List;

public class Stream06CountDistinctCharacters {

    public static void main(String[] args) {

        List<String> words = List.of(
                "java",
                "stream",
                "lambda"
        );

        long count = words.stream()
                .flatMap(s ->
                        s.chars()
                                .mapToObj(c -> (char) c))
                .distinct()
                .count();

        System.out.println("Distinct characters = " + count);
    }
}