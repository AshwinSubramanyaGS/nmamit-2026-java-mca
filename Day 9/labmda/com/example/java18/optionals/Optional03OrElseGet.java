package com.example.java18.optionals;

import java.util.Optional;

public class Optional03OrElseGet {

    private static String expensiveDefault() {

        System.out.println("Generating default...");
        return "DEFAULT";
    }

    public static void main(String[] args) {

        Optional<String> present =
                Optional.of("Java");

        Optional<String> empty =
                Optional.empty();

        System.out.println(
                present.orElseGet(
                        Optional03OrElseGet::expensiveDefault));

        System.out.println(
                empty.orElseGet(
                        Optional03OrElseGet::expensiveDefault));
    }
}