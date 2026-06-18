package com.example.java18.streams;

import java.util.List;

public class Stream01SumSquaresEven {
     public static void main(String[] args) {

        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6);

        int sum = nums.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(n -> n * n)
                .sum();

        System.out.println("Sum = " + sum);
    }
}
