package com.tfg.boulder_back.constants;

import lombok.Getter;

@Getter
public enum TypeUser {

    USER("User"),
    WORKER("Worker"),
    ADMIN("Admin");

    private final String type;

    TypeUser(String type) {
        this.type = type;
    }

}
