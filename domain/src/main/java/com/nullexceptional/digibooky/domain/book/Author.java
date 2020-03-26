package com.nullexceptional.digibooky.domain.book;

import org.springframework.stereotype.Component;

import java.util.UUID;


public class Author {
    private final UUID id;
    private final String lastName;
    private final String firstName;

    public Author(String lastName, String firstName) {
        this.id = UUID.randomUUID();
        this.lastName = lastName;
        this.firstName = firstName;
    }
}
