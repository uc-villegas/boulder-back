package com.tfg.boulder_back.dto;

import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DetailedUserDTO {

    private Long idUser;
    private String name;
    private String surname;
    private String email;
    private List<DetailedVideoDTO> videos;
}
