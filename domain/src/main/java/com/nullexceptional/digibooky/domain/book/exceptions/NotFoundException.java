package com.nullexceptional.digibooky.domain.book.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String searchString) {
        super("Your search for: " + searchString + " did not result in any matches.");
    }
}
