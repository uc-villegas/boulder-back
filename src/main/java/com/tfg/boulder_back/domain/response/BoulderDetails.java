package com.tfg.boulder_back.domain.response;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoulderDetails {

    private Long idBoulder;
    private String name;
    private String address;
    private String mail;
    private String phone;
    private String phone2;

    private List<RouteDetails> routes;
}
