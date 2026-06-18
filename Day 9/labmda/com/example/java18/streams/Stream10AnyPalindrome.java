package com.example.java18.streams;

import java.util.List;

public class Stream10AnyPalindrome {

    public static void main(String[] args) {

        List<String> words = List.of(
                "java",
                "level",
                "spring",
                "radar"
        );

        boolean palindromeExists = words.stream()
                .anyMatch(word ->
                        word.equals(
                                new StringBuilder(word)
                                        .reverse()
                                        .toString()));

        System.out.println(
                "Palindrome found = "
                        + palindromeExists);
    }
}