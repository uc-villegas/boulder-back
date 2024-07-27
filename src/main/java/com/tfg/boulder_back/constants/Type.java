package com.tfg.boulder_back.constants;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public enum Type {

    BOULDER("Boulder"),
    WALL_ROUTE("Wall route");

    private final String type;

    Type(String type) {
        this.type = type;
    }

    public static List<Type> getValues(){
        return List.of(Type.values());
    }


}
