package com.nullexceptional.digibooky.domain.book;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(lastName, author.lastName) &&
                Objects.equals(firstName, author.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName);
    }
}
