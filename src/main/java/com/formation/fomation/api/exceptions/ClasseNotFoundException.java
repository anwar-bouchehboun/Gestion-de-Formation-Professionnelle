package com.formation.fomation.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ClasseNotFoundException extends RuntimeException {
    public ClasseNotFoundException(String message) {
        super(message);
    }
}