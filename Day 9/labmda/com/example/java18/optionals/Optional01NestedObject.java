package com.example.java18.optionals;

import com.example.java18.model.Address;
import com.example.java18.model.Person;

import java.util.Optional;

public class Optional01NestedObject {

    public static String getCityName(Person person) {

        return Optional.ofNullable(person)
                .map(Person::getAddress)
                .map(Address::getCity)
                .orElse("Unknown");
    }

    public static void main(String[] args) {

        Person p1 = new Person(new Address("Bangalore"));
        Person p2 = null;

        System.out.println(getCityName(p1));
        System.out.println(getCityName(p2));
    }
}