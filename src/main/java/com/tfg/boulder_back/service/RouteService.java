package com.tfg.boulder_back.service;

import com.tfg.boulder_back.domain.request.AddRouteRequest;
import com.tfg.boulder_back.dto.DetailedRouteDTO;
import com.tfg.boulder_back.dto.RoutesDTO;
import com.tfg.boulder_back.entity.Route;

import java.util.List;

public interface RouteService {

    List<RoutesDTO> findAllRoutesById(Long idBoulder);
    Route addRouteAndLoadData(AddRouteRequest addRouteRequest);
    DetailedRouteDTO findByIdRouteNameBoulder(String nameBoulder, Long idRoute);

    //Route updateRoute(Long idBoulder, Long idRoute, UpdateRouteRequest request);
    void deleteRoute(Long idBoulder, Long idRoute);

}
