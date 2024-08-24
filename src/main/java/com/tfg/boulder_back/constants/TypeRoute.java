package com.tfg.boulder_back.constants;

import lombok.Getter;

@Getter
public enum TypeRoute {

    BOULDER("Boulder"),
    WALL_ROUTE("Wall route");

    private final String type;

    TypeRoute(String type) {
        this.type = type;
    }

}
