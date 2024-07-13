package com.tfg.boulder_back.domain.response;

import lombok.*;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RouteDetails {

    private Long idRoute;
    private String name;
    private String difficulty;
    private String color;

    private List<VideoDetails> videos;
}
