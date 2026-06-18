package com.example.java18.optionals;

import com.example.java18.model.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Optional05SortEmployees{

    public static void main(String[] args) {

        List<Employee> employees =
                new ArrayList<>(List.of(
                        new Employee("John", 30),
                        new Employee("Alice", 25),
                        new Employee("Bob", 40)
                ));

        employees.sort(
                Comparator.comparingInt(
                        Employee::getAge));

        employees.forEach(System.out::println);
    }
}