package com.example.springonspring.rest.exceptions;

/**
 * Representing a base abstract exception class for this app.
 */
public class GreetingException extends RuntimeException {

    public GreetingException() {
        super();
    }

    public GreetingException(String message) {
        super(message);
    }

}
