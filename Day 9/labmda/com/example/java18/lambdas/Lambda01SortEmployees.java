package com.example.java18.lambdas;

import com.example.java18.model.Employee;
import java.util.*;

public class Lambda01SortEmployees {

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>(List.of(
                new Employee("John", 25),
                new Employee("John", 35),
                new Employee("Alice", 30),
                new Employee("Bob", 28)
        ));

        employees.sort((e1,e2)->e1.getName().compareTo(e2.getName()));
        employees.forEach(System.out::println);

        System.out.println("\nDescending age:");

        employees.sort((e1,e2)->Integer.compare(e2.getAge(), e1.getAge()));
        employees.forEach(System.out::println);
    }
}