package com.nullexceptional.digibooky.domain.book.exceptions;

public class IsbnNotFoundException extends RuntimeException{

    public IsbnNotFoundException(String isbn) {
        super("Your book with ISBN: " + isbn + " could not be found.");
    }
}
