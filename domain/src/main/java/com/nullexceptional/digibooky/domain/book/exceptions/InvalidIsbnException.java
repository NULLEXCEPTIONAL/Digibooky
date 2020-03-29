package com.nullexceptional.digibooky.domain.book.exceptions;

public class InvalidIsbnException extends RuntimeException {

    public InvalidIsbnException(String message) {
        super(message);
    }
}
