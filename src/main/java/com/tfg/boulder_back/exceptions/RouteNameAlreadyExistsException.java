package com.tfg.boulder_back.exceptions;

public class RouteNameAlreadyExistsException extends RuntimeException{

    public RouteNameAlreadyExistsException(String message) {
        super(message);
    }

}
