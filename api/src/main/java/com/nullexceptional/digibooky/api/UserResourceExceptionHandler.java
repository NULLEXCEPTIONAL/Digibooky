package com.nullexceptional.digibooky.api;

import com.nullexceptional.digibooky.service.members.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class UserResourceExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

//    example of an IllegalStateException handler
//    you can change the exception type to your liking
//    if you throw an exception in your domain or service layer don't forget to add a handler here so our code doesn't crash
//
    @ExceptionHandler(IllegalStateException.class)
    protected void illegalStateException(IllegalStateException exception, HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), exception.getMessage());
    }



}
