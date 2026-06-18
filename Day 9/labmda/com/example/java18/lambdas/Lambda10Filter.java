package com.example.java18.lambdas;

import java.util.*;
import java.util.function.Predicate;

public class Lambda10Filter {

    public static <T> List<T> filter(
            List<T> list,
            Predicate<T> predicate) {

        List<T> result = new ArrayList<>();

        for (T item : list) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }

        return result;
    }

    public static void main(String[] args) {

        System.out.println(
                filter(
                        List.of(5, 12, 25, 7, 30),
                        n -> n > 10));
    }
}