package com.nullexceptional.digibooky.domain.book.exceptions;

public class NoBookToUpdateException extends RuntimeException {

    public NoBookToUpdateException(String message) {
        super(message);
    }
}
