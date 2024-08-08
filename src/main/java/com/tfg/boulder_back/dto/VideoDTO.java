package com.tfg.boulder_back.dto;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VideoDTO {

    private Long id;
    private String title;
    private String description;
    private String url;
    private int duration;
    private UserDTO user;

}
