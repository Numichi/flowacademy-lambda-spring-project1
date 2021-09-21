package com.example.demo.controllers;

import com.example.demo.exceptions.WrongPasswordException;
import com.example.demo.model.ErrorModel;
import com.example.demo.services.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ErrorController {

    private static final Logger log = LogManager.getLogger("AUDIT");

    @ExceptionHandler({NoSuchElementException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorModel noSuchElementException(NoSuchElementException exception) {
        log.info(exception.getMessage());

        var error = new ErrorModel();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        return error;
    }

    @ExceptionHandler({WrongPasswordException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorModel notAcceptPassword() {
        var error = new ErrorModel();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage("Not valid password!!!!!!!!!!!!!!!!");
        return error;
    }
}
