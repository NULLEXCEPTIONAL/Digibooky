package com.nullexceptional.digibooky.domain.book;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.stereotype.Component;

import java.util.UUID;

@JsonAutoDetect
public class Author {
    private final UUID id;
    private final String lastName;
    private final String firstName;

    public Author(String lastName, String firstName) {
        this.id = UUID.randomUUID();
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public UUID getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }
}
