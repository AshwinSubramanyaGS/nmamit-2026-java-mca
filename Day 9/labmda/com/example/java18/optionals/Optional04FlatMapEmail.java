package com.example.java18.optionals;

import com.example.java18.model.User;

import java.util.Optional;

public class Optional04FlatMapEmail {

    public static void main(String[] args) {

        Optional<User> user =
                Optional.of(
                        new User("john@gmail.com"));

        Optional<String> email = user
                .flatMap(User::getEmail)
                .filter(e -> !e.isBlank());

        System.out.println(email);

        Optional<User> noEmail =
                Optional.of(new User(null));

        System.out.println(
                noEmail
                        .flatMap(User::getEmail));
    }
}