package com.example.java18.lambdas;

import java.util.*;
import java.util.function.Supplier;

public class Lambda07GenerateNumbers {

    public static List<Integer> generate(
            int n,
            Supplier<Integer> supplier) {

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            result.add(supplier.get());
        }

        return result;
    }

    public static void main(String[] args) {

        Random random = new Random();

        List<Integer> numbers =
                generate(10,
                        () -> random.nextInt(100) + 1);

        System.out.println(numbers);
    }
}