package com.tfg.boulder_back.service;

import com.tfg.boulder_back.entity.Route;

import java.util.List;
import java.util.Optional;

public interface RouteService {

    Route addRoute(Route route);
    Optional<Route> findByIdRoute(Long idRoute);
    List<Route> findAllRoutes();
    Route updateRoute(Route route);
    void deleteRoute(Long idRoute);

}
