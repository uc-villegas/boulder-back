package com.tfg.boulder_back.dto;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DetailedBoulderDTO {

    private Long idBoulder;
    private String name;
    private String address;
    private String locality;
    private String mail;
    private String phone;

}
