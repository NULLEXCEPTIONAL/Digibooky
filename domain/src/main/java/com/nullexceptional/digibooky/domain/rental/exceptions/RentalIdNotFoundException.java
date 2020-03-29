package com.nullexceptional.digibooky.domain.rental.exceptions;

public class RentalIdNotFoundException extends RuntimeException {

    public RentalIdNotFoundException(String message) {
        super(message);
    }
}
