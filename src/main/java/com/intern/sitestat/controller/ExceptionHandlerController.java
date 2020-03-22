package com.intern.sitestat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionHandlerController {

    /**
     * Method for mapping the exception to the response of the bad request.
     *
     * @param exception the incoming exception for mapping
     * @return {@code String} message from the exception
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public String exceptionHandler(Exception exception) {
        return exception.getMessage();
    }
}
