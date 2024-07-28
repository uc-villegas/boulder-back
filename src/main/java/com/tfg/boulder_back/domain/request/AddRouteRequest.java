package com.tfg.boulder_back.domain.request;

import com.tfg.boulder_back.constants.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddRouteRequest {


    @NotNull
    private Long idBoulder;

    @NotNull
    private String qrRoute;

    @NotNull
    private String name;

    @Schema(implementation = Type.class)
    private Type type;

    @NotNull
    private int num_nivel; // numerico

    @NotNull
    private String presa; // Colores
}
