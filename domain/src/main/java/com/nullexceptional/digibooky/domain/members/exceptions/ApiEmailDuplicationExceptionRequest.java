package com.nullexceptional.digibooky.domain.members.exceptions;

public class ApiEmailDuplicationExceptionRequest extends RuntimeException {
    public ApiEmailDuplicationExceptionRequest(String message) {
        super(message);
    }
}
