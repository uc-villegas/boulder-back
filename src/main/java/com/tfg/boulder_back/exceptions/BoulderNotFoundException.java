package com.tfg.boulder_back.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class BoulderNotFoundException extends RuntimeException {

    public BoulderNotFoundException() {
        super();
    }

    public BoulderNotFoundException(String message) {
        super(message);
    }

    public BoulderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BoulderNotFoundException(Throwable cause) {
        super(cause);
    }
}
