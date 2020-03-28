package com.nullexceptional.digibooky.domain.book.exceptions;

public class IsbnNotFoundException extends RuntimeException{

    public IsbnNotFoundException(String message) {
        super(message);
    }
}
