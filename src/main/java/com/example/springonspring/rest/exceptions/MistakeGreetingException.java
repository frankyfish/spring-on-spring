package com.example.springonspring.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Some dummy exception to use from controller.
 * See {@link com.example.springonspring.rest.GreetingAdvice}
 */
@ResponseStatus(HttpStatus.I_AM_A_TEAPOT) // overridden by advice
public class MistakeGreetingException extends GreetingException {

    public MistakeGreetingException(String message) {
        super(message);
    }

}
