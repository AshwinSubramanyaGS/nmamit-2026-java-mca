package com.example.java18.optionals;

import java.util.List;
import java.util.function.Predicate;

public class Optional09PredicateNot {

    public static void main(String[] args) {

        List<String> values =
                List.of(
                        "Java",
                        "",
                        "Spring",
                        "",
                        "Docker");

        Predicate<String> nonEmpty =
                Predicate.not(String::isEmpty);

        List<String> result =
                values.stream()
                        .filter(nonEmpty)
                        .toList();

        System.out.println(result);
    }
}