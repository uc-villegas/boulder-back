package com.tfg.boulder_back.service.impl;

import com.tfg.boulder_back.entity.Route;
import com.tfg.boulder_back.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RouteServiceImpl implements RouteService {

    @Override
    public Route addRoute(Route route) {
        return null;
    }

    @Override
    public Optional<Route> findByIdRoute(Long idRoute) {
        return Optional.empty();
    }

    @Override
    public List<Route> findAllRoutes() {
        return List.of();
    }

    @Override
    public Route updateRoute(Route route) {
        return null;
    }

    @Override
    public void deleteRoute(Long idRoute) {

    }
}
