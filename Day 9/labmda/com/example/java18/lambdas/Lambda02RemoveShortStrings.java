package com.example.java18.lambdas;
import java.util.*;

public class Lambda02RemoveShortStrings {

    public static void main(String[] args) {

        List<String> words = new ArrayList<>(
                List.of("a", "code", "java", "is", "fun"));

        words.removeIf(word -> word.length() < 4);

        System.out.println(words);
    }
}