package com.example.java18.optionals;

import java.util.List;

public class Optional06StringToInteger {
    
    public static void main(String[] args) {

        List<String> strings =
                List.of("10", "20", "30", "40");

        List<Integer> numbers =
                strings.stream()
                        .map(Integer::parseInt)
                        .toList();

        System.out.println(numbers);
    }
}