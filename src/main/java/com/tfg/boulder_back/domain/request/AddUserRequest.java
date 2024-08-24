package com.tfg.boulder_back.domain.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AddUserRequest {

    // TODO: Hacerlo como con AddRouteRequest

    @NotEmpty
    private String idUser;

    @NotEmpty
    private String fullName;
}
