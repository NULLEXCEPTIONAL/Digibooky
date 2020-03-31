package com.nullexceptional.digibooky.api;

import com.nullexceptional.digibooky.domain.book.exceptions.NonExistingUserException;
import com.nullexceptional.digibooky.domain.rental.exceptions.RentalIdNotFoundException;
import com.nullexceptional.digibooky.service.rental.RentalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class RentalResourceExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(RentalService.class);

    @ExceptionHandler(IllegalStateException.class)
    protected void illegalStateException(IllegalStateException exception, HttpServletResponse response) throws IOException {
        LOGGER.info(exception.getMessage(), exception);
        response.sendError(BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(RentalIdNotFoundException.class)
    protected void rentalIdNotFoundException(RentalIdNotFoundException exception, HttpServletResponse response) throws IOException {
        LOGGER.info(exception.getMessage(), exception);
        response.sendError(BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(NonExistingUserException.class)
    protected void rentalIdNotFoundException(NonExistingUserException exception, HttpServletResponse response) throws IOException {
        LOGGER.info(exception.getMessage(), exception);
        response.sendError(BAD_REQUEST.value(), exception.getMessage());
    }

}
