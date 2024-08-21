package com.tfg.boulder_back.dto;

import com.tfg.boulder_back.constants.TypeUser;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserHomeDTO {

    private Long idUser;
    private String name;
    private String surname;
    private String email;
    private TypeUser role;
    private DetailedBoulderDTO boulder;
}
