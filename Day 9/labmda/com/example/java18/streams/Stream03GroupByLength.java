package com.example.java18.streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stream03GroupByLength {

    public static void main(String[] args) {

        List<String> words = List.of(
                "java",
                "code",
                "spring",
                "api",
                "docker"
        );

        Map<Integer, List<String>> grouped = words.stream()
                .collect(Collectors.groupingBy(String::length));

        grouped.forEach((length, values) ->
                System.out.println(length + " -> " + values));
    }
}