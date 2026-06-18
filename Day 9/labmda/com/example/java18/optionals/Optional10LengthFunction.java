package com.example.java18.optionals;

import java.util.List;
import java.util.function.Function;

public class Optional10LengthFunction {

    public static void main(String[] args) {

        Function<String, Integer> lengthFunction =
                String::length;

        System.out.println(
                lengthFunction.apply("Java"));

        List<String> words =
                List.of(
                        "Java",
                        "Spring",
                        "Docker");

        List<Integer> lengths =
                words.stream()
                        .map(String::length)
                        .toList();

        System.out.println(lengths);
    }
}