package com.nullexceptional.digibooky.domain.members.exceptions;

public class ApiEmailRequestException extends RuntimeException {
    public ApiEmailRequestException(String message) {
        super(message);
    }
}
