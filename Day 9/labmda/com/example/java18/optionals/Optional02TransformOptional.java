package com.example.java18.optionals;

import java.util.Optional;

public class Optional02TransformOptional {

    public static void main(String[] args) {

        Optional<Integer> number = Optional.of(5);

        Optional<Integer> squared =
                number.map(n -> n * n);

        System.out.println(squared);

        Optional<Integer> empty = Optional.empty();

        System.out.println(
                empty.map(n -> n * n)
        );
    }
}