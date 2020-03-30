package com.nullexceptional.digibooky.api;

import com.nullexceptional.digibooky.domain.book.exceptions.BookAlreadyExistsException;
import com.nullexceptional.digibooky.domain.book.exceptions.InvalidIsbnException;
import com.nullexceptional.digibooky.domain.book.exceptions.NoBookToUpdateException;
import com.nullexceptional.digibooky.domain.book.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class BookResourceExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(BookResourceExceptionHandler.class);


    @ExceptionHandler(BookAlreadyExistsException.class)
    protected void bookAlreadyExistsException(BookAlreadyExistsException exception, HttpServletResponse response) throws IOException {
        logger.info(exception.getMessage());
        response.sendError(BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(NoBookToUpdateException.class)
    protected void noBookToUpdateWithThatISBN(NoBookToUpdateException exception, HttpServletResponse response) throws IOException {
        logger.info(exception.getMessage());
        response.sendError(BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(InvalidIsbnException.class)
    protected void invalidIsbn(InvalidIsbnException exception, HttpServletResponse response) throws IOException {
        logger.info(exception.getMessage());
        response.sendError(BAD_REQUEST.value(), exception.getMessage());
    }
}
