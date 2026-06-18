package com.example.java18.streams;


import java.util.List;

public class Stream09AverageAboveThreshold {

    public static void main(String[] args) {

        int threshold = 10;

        List<Integer> nums = List.of(
                5, 12, 20, 8, 30, 15
        );

        double average = nums.stream()
                .filter(n -> n > threshold)
                .mapToDouble(n -> n)
                .average()
                .orElse(0.0);

        System.out.println(
                "Average = " + average);
    }
}