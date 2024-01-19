package org.nicolau.biddingscrapinggov.config.exception;

import org.nicolau.biddingscrapinggov.config.exception.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> userNotFoundException(NotFoundException exception) {
        return new ResponseEntity(new ExceptionDetails(ErrorMessage.BIDDING_NOT_FOUND, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }
}
