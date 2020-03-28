package com.nullexceptional.digibooky.domain.book.exceptions;

public class TitleNotFoundException extends RuntimeException {

    public TitleNotFoundException(String message) {
        super(message);
    }
}
