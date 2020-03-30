package com.nullexceptional.digibooky.domain.book.exceptions;

import java.util.UUID;

public class BookAlreadyExistsException extends RuntimeException {

    public BookAlreadyExistsException(String title, String isbn) {
        super("Log ID: " + UUID.randomUUID() + " Book " + title + " with ISBN " + isbn + " is already registered.");
    }
}
