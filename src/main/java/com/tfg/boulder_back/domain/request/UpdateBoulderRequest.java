package com.tfg.boulder_back.domain.request;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBoulderRequest {

    private String name;

    private String address;

    private String locality;

    private String mail;

    private String phone;

    private String phone2;
}
