package com.example.java18.streams;

import java.util.List;

public class Stream05FlattenAndSum {

    public static void main(String[] args) {

        List<List<Integer>> numbers = List.of(
                List.of(1, 2, 3),
                List.of(4, 5),
                List.of(6, 7, 8)
        );

        int total = numbers.stream()
                .flatMapToInt(
                        list -> list.stream()
                                .mapToInt(Integer::intValue))
                .sum();

        System.out.println("Total = " + total);
    }
}