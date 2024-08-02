package com.tfg.boulder_back.dto;

import com.tfg.boulder_back.constants.Type;
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
    private Type type;
    private int num_nivel; // numerico
    private String presa; // Colores
    private Date creationDate;
    private List<VideoDTO> videos;
}
