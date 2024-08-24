package com.tfg.boulder_back.dto;

import com.tfg.boulder_back.constants.TypeRoute;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RouteEditDTO {

    private String qrRoute;
    private String name;
    private TypeRoute typeRoute;
    private Integer num_nivel; // Integer para permitir nulos.
    private String presa; // Colores

}
