package com.example.java18.lambdas;

import java.util.*;

public class Lambda06MergeMaps {

    public static void main(String[] args) {

        Map<String, Integer> m1 = new HashMap<>();
        m1.put("A", 10);
        m1.put("B", 20);

        Map<String, Integer> m2 = new HashMap<>();
        m2.put("B", 5);
        m2.put("C", 15);

        m2.forEach(
                (k, v) ->
                        m1.merge(k, v, Integer::sum));

        System.out.println(m1);
    }
}