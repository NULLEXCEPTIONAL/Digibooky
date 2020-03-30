package com.nullexceptional.digibooky.domain.book.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String searchString) {
        super("Log ID: " + UUID.randomUUID() + "Your search for: " + searchString + " did not result in any matches.");
    }
}
