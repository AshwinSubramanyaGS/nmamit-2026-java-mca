package com.example.java18.model;

import java.util.Optional;

public class User {

    private final Optional<String> email;

    public User(String email) {
        this.email = Optional.ofNullable(email);
    }

    public Optional<String> getEmail() {
        return email;
    }
}