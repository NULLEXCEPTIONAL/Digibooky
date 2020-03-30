package com.nullexceptional.digibooky.domain.book.exceptions;

import java.util.UUID;

public class NoBookToUpdateException extends RuntimeException {

    public NoBookToUpdateException(String isbn) {
        super("Log ID: " + UUID.randomUUID() + " There is no book with ISBN " + isbn + " to delete");
    }
}
