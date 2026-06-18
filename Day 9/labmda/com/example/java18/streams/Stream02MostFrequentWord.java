package com.example.java18.streams;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Stream02MostFrequentWord {

    public static void main(String[] args) {

        List<String> words = List.of(
                "java",
                "spring",
                "java",
                "docker",
                "java",
                "spring"
        );

        String mostFrequent = words.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        System.out.println("Most frequent word = " + mostFrequent);
    }
}