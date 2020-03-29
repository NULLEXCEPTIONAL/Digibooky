package com.nullexceptional.digibooky.api;

import com.nullexceptional.digibooky.domain.members.exceptions.ApiEmailDuplicationExceptionRequest;
import com.nullexceptional.digibooky.domain.members.exceptions.ApiEmailExceptionPayload;
import com.nullexceptional.digibooky.domain.members.exceptions.ApiEmailRequestException;
import com.nullexceptional.digibooky.domain.members.exceptions.DuplicationInssException;
import com.nullexceptional.digibooky.service.members.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class UserResourceExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

//    example of an IllegalStateException handler
//    you can change the exception type to your liking
//    if you throw an exception in your domain or service layer don't forget to add a handler here so our code doesn't crash
//
    @ExceptionHandler(value = {ApiEmailRequestException.class})
    public ResponseEntity<Object> handleApiEmailRequestException(ApiEmailRequestException e){
        HttpStatus badRequest = BAD_REQUEST;
        ApiEmailExceptionPayload emailException = new ApiEmailExceptionPayload(e.getMessage(), badRequest, ZonedDateTime.now());
        LOGGER.warn(e.getMessage());
        return new ResponseEntity<>(emailException, badRequest);
    }
    @ExceptionHandler(value = {ApiEmailDuplicationExceptionRequest.class})
    public ResponseEntity<Object> handleApiEmailDuplicationRequestException(ApiEmailDuplicationExceptionRequest e){
        HttpStatus conflictRequest = HttpStatus.CONFLICT;
        ApiEmailExceptionPayload emailException = new ApiEmailExceptionPayload(e.getMessage(), conflictRequest, ZonedDateTime.now());
        LOGGER.warn(e.getMessage());
        return new ResponseEntity<>(emailException, conflictRequest);
    }
    @ExceptionHandler(value = {DuplicationInssException.class})
    public ResponseEntity<Object> handleDuplicationInssException(DuplicationInssException e){
        HttpStatus conflictRequest = HttpStatus.CONFLICT;
        ApiEmailExceptionPayload emailException = new ApiEmailExceptionPayload(e.getMessage(), conflictRequest, ZonedDateTime.now());
        LOGGER.warn(e.getMessage());
        return new ResponseEntity<>(emailException, conflictRequest);
    }




}
