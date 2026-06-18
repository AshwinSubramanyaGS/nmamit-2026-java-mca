package com.example.java18.streams;

import java.util.Comparator;
import java.util.List;

public class Stream07SecondHighestNumber {

    public static void main(String[] args) {

        List<Integer> nums = List.of(
                10, 45, 2, 89, 63, 25
        );

        int secondHighest = nums.stream()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException(
                                "No second highest value"));

        System.out.println(
                "Second highest = " + secondHighest);
    }
}