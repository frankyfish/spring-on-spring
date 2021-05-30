package com.example.springonspring.rest.exceptions;

/**
 * Used to notify client that invalid data has been submitted.
 */
public class InvalidGreetingContentValueException extends GreetingException {

    public InvalidGreetingContentValueException(String message) {
        super(message);
    }

}
