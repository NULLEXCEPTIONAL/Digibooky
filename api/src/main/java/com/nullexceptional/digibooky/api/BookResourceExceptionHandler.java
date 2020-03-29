package com.nullexceptional.digibooky.api;

import com.nullexceptional.digibooky.domain.book.exceptions.BookAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@ControllerAdvice
public class BookResourceExceptionHandler {

//    example of an IllegalStateException handler
//    you can change the exception type to your liking
//    if you throw an exception in your domain or service layer don't forget to add a handler here so our code doesn't crash
//
//    @ExceptionHandler(IllegalStateException.class)
//    protected void illegalStateException(IllegalStateException exception, HttpServletResponse response) throws IOException{
//        response.sendError(BAD_REQUEST.value(), exception.getMessage());
//    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    protected void bookAlreadyExistsException(BookAlreadyExistsException exception, HttpServletResponse response) throws IOException{
        response.sendError(FORBIDDEN.value(), exception.getMessage());
    }
}
