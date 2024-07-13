package com.tfg.boulder_back.domain.response;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

    private String idUser;
    private String name;
    private String surname;
    private String email;
}
