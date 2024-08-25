package com.tfg.boulder_back.dto;

import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BouldersInfo {

    private String name;
    private List<RouteInfo> routeList;
}
