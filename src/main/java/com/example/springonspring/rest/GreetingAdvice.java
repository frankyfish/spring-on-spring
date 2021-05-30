package com.example.springonspring.rest;

import com.example.springonspring.rest.exceptions.InvalidGreetingContentValueException;
import com.example.springonspring.rest.exceptions.MistakeGreetingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GreetingAdvice {

    /**
     * Handles dummy exceptions
     * @return response entity with string body and {@link HttpStatus#I_AM_A_TEAPOT}
     */
    @ExceptionHandler(MistakeGreetingException.class)
    public ResponseEntity<String> handleMistake() {
        String body = "error you've taken the wrong path";
        return new ResponseEntity<>(body, HttpStatus.I_AM_A_TEAPOT);
    }

    /**
     * Handles exceptions regarding parameters validation.
     * @param e exception that was thrown
     * @return http entity with exception's message and code 400
     */
    @ExceptionHandler(InvalidGreetingContentValueException.class)
    public ResponseEntity<String> handleInvalidValue(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
