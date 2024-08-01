package com.tfg.boulder_back.service;

import com.tfg.boulder_back.domain.request.AddRouteRequest;
import com.tfg.boulder_back.entity.Route;

import java.util.List;
import java.util.Optional;

public interface RouteService {

    Route addRoute(Route route);
    Optional<Route> findByIdRoute(Long idRoute);
    List<Route> findAllRoutes();
    Route updateRoute(Route route);
    void deleteRoute(Long idRoute);
    List<Route> findAllRoutesById(Long idBoulder);
    Route addRouteAndLoadData(AddRouteRequest addRouteRequest);
    Route findByIdRouteIdBoulder(Long idBoulder, Long idRoute);

}
