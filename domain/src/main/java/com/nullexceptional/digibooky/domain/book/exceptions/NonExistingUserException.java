package com.nullexceptional.digibooky.domain.book.exceptions;

public class NonExistingUserException extends RuntimeException{

    public NonExistingUserException(String message) {
        super(message);
    }
}
