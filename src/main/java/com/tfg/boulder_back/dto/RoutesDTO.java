package com.tfg.boulder_back.dto;


import com.tfg.boulder_back.constants.TypeRoute;
import lombok.*;

import java.util.Date;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoutesDTO {

    private Long idRoute;
    private String qrRoute;
    private String name;
    private TypeRoute typeRoute;
    private int num_nivel; // numerico
    private String presa; // Colores
    private Date creationDate;
}
