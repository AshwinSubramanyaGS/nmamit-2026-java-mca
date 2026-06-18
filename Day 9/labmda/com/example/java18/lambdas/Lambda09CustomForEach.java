package com.example.java18.lambdas;

import java.util.*;
import java.util.function.Consumer;

public class Lambda09CustomForEach {

    public static <T> void forEach(
            List<T> list,
            Consumer<T> consumer) {

        for (T item : list) {
            consumer.accept(item);
        }
    }

    public static void main(String[] args) {

        forEach(
                List.of("java", "lambda", "stream"),
                s -> System.out.println(s.toUpperCase()));
    }
}