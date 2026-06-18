package com.example.java18.optionals;

import com.example.java18.model.Student;

import java.util.List;

public class Optional07ConstructorReference {

    public static void main(String[] args) {

        List<String> names =
                List.of(
                        "John",
                        "Alice",
                        "Bob");

        List<Student> students =
                names.stream()
                        .map(Student::new)
                        .toList();

        students.forEach(System.out::println);
    }
}