package com.tfg.boulder_back.dto;

import com.tfg.boulder_back.constants.TypeRoute;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DetailedRouteDTO {

    private Long idRoute;
    private String qrRoute;
    private String name;
    private TypeRoute typeRoute;
    private int num_nivel; // numerico
    private String presa; // Colores
    private Date creationDate;
    private List<DetailedVideoDTO> videos;
}
