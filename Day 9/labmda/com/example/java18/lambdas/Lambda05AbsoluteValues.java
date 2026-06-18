package com.example.java18.lambdas;

import java.util.*;

public class Lambda05AbsoluteValues {

    public static void main(String[] args) {

        List<Integer> numbers =
                new ArrayList<>(List.of(-5, 10, -15, 20));

        numbers.replaceAll(Math::abs);

        System.out.println(numbers);
    }
}