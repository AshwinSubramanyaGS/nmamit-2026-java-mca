package com.example.java18.lambdas;

import java.util.function.BinaryOperator;

public class Lambda08MaxBinaryOperator {

    public static int max(
            int a,
            int b,
            BinaryOperator<Integer> operator) {

        return operator.apply(a, b);
    }

    public static void main(String[] args) {

        System.out.println(
                max(15, 8,
                        (x, y) -> x > y ? x : y));
    }
}