package com.tfg.boulder_back.domain.request;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;
}
