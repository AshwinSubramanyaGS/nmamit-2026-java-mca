package com.example.java18.optionals;

@FunctionalInterface
interface StringFormatter {

    String format(String value);
}

public class Optional08StringFormatter {

    public static void main(String[] args) {

        StringFormatter formatter =
                value -> "[" + value + "]";

        System.out.println(
                formatter.format("hello"));
    }
}