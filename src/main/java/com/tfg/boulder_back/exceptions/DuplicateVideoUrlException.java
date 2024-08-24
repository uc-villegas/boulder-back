package com.tfg.boulder_back.exceptions;

public class DuplicateVideoUrlException extends RuntimeException {

    public DuplicateVideoUrlException(String message) {
        super(message);
    }
}
