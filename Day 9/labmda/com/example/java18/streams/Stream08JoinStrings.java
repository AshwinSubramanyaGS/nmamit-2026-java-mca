package com.example.java18.streams;

import java.util.List;
import java.util.stream.Collectors;

public class Stream08JoinStrings {

    public static void main(String[] args) {

        List<String> words = List.of(
                "Java",
                "Spring",
                "Docker",
                "Kubernetes"
        );

        String result = words.stream()
                .collect(Collectors.joining(", "));

        System.out.println(result);
    }
}