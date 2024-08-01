package com.tfg.boulder_back.service.impl;

import com.tfg.boulder_back.domain.request.AddRouteRequest;
import com.tfg.boulder_back.entity.Boulder;
import com.tfg.boulder_back.entity.Route;
import com.tfg.boulder_back.exceptions.BoulderNotFoundException;
import com.tfg.boulder_back.exceptions.RouteNotFoundException;
import com.tfg.boulder_back.repository.BoulderRepository;
import com.tfg.boulder_back.repository.RouteRepository;
import com.tfg.boulder_back.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final BoulderRepository boulderRepository;

    public RouteServiceImpl(RouteRepository routeRepository, BoulderRepository boulderRepository) {
        this.routeRepository = routeRepository;
        this.boulderRepository = boulderRepository;
    }

    @Override
    public Route addRouteAndLoadData(AddRouteRequest routeRequest) {

        Optional<Boulder> optionalBoulder = boulderRepository.findById(routeRequest.getIdBoulder());

        if (optionalBoulder.isEmpty()) {
            throw new BoulderNotFoundException("Boulder not found with ID: " + routeRequest.getIdBoulder());
        }

        log.info("Boulder found with ID: " + routeRequest.getIdBoulder());
        Boulder boulder = optionalBoulder.get();

        Route route = new Route();
        route.setQrRoute(routeRequest.getQrRoute());
        route.setName(routeRequest.getName());
        route.setType(routeRequest.getType());
        route.setPresa(routeRequest.getPresa());
        route.setCreationDate(new Date());
        route.setNum_nivel(routeRequest.getNum_nivel());
        route.setBoulder(boulder);
        route.setVideos(new HashSet<>());

        routeRepository.save(route);
        //boulder.getRoutes().add(route);
        //route.setBoulder(boulder);
        boulderRepository.save(boulder);

        return route;
    }


    @Override
    public Route addRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public Optional<Route> findByIdRoute(Long idRoute) {
        return Optional.empty();
    }

    @Override
    public List<Route> findAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Route updateRoute(Route route) {
        return null;
    }

    @Override
    public void deleteRoute(Long idRoute) {

    }

    @Override
    public List<Route> findAllRoutesById(Long idBoulder) {

        Optional<Boulder> optionalBoulder = boulderRepository.findById(idBoulder);

        if (optionalBoulder.isEmpty()) {
            throw new BoulderNotFoundException("Boulder not found with ID: " + idBoulder);
        }

        Boulder boulder = optionalBoulder.get();
        return routeRepository.findAllByIdBoulder(boulder.getIdBoulder());
    }

    @Override
    public Route findByIdRouteIdBoulder(Long idBoulder, Long idRoute){

        Optional<Boulder> optionalBoulder = boulderRepository.findById(idBoulder);

        if (optionalBoulder.isEmpty()) {
            throw new BoulderNotFoundException("Boulder not found with ID: " + idBoulder);
        }

        Optional<Route> optionalRoute = routeRepository.findById(idRoute);

        if (optionalRoute.isEmpty()) {
            throw new RouteNotFoundException("Route not found with ID: " + idRoute);
        }
        
        return optionalRoute.get();
    }
}
