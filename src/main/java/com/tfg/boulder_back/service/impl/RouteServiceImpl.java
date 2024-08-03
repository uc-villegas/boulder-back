package com.tfg.boulder_back.service.impl;

import com.tfg.boulder_back.domain.request.AddRouteRequest;
import com.tfg.boulder_back.dto.*;
import com.tfg.boulder_back.entity.Boulder;
import com.tfg.boulder_back.entity.Route;
import com.tfg.boulder_back.entity.Video;
import com.tfg.boulder_back.exceptions.BoulderNotFoundException;
import com.tfg.boulder_back.exceptions.RouteNotFoundException;
import com.tfg.boulder_back.repository.BoulderRepository;
import com.tfg.boulder_back.repository.RouteRepository;
import com.tfg.boulder_back.repository.VideoRepository;
import com.tfg.boulder_back.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private BoulderRepository boulderRepository;

    @Autowired
    private VideoRepository videoRepository;


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

        routeRepository.save(route);
        boulderRepository.save(boulder);

        return route;
    }

    @Override
    public List<RoutesDTO>  findAllRoutesById(Long idBoulder) {

        Optional<Boulder> optionalBoulder = boulderRepository.findById(idBoulder);

        if (optionalBoulder.isEmpty()) {
            throw new BoulderNotFoundException("Boulder not found with ID: " + idBoulder);
        }

        List<Route> routes = routeRepository.findAllByIdBoulder(idBoulder);

        return routes.stream().map(this::convertToRoutesDTO).collect(Collectors.toList());    }

    @Override
    public DetailedRouteDTO findByIdRouteIdBoulder(Long idBoulder, Long idRoute){

        DetailedRouteDTO detailedRouteDTO = new DetailedRouteDTO();

        Optional<Boulder> optionalBoulder = boulderRepository.findById(idBoulder);
        if (optionalBoulder.isEmpty()) {
            throw new BoulderNotFoundException("Boulder not found with ID: " + idBoulder);
        }

        Optional<Route> optionalRoute = routeRepository.findById(idRoute);

        if (optionalRoute.isEmpty()) {
            throw new RouteNotFoundException("Route not found with ID: " + idRoute);
        }

        Route route = optionalRoute.get();
        List<Video> videos = videoRepository.findAllById(Collections.singleton(idRoute)); // TODO: revisar repository, aqui se busca por idRoute, en UserServiceImpl se busca por idUser.

        detailedRouteDTO.setIdRoute(route.getIdRoute());
        detailedRouteDTO.setName(route.getName());
        detailedRouteDTO.setQrRoute(route.getQrRoute());
        detailedRouteDTO.setCreationDate(route.getCreationDate());
        detailedRouteDTO.setPresa(route.getPresa());
        detailedRouteDTO.setType(route.getType());
        detailedRouteDTO.setNum_nivel(route.getNum_nivel());

        List<DetailedVideoDTO> detailedVideosDTO = videos.stream().map(this::convertToDetailedVideoDTO).toList();
        detailedRouteDTO.setVideos(detailedVideosDTO);

        return detailedRouteDTO;
    }


    private RoutesDTO convertToRoutesDTO(Route route) {
        RoutesDTO dto = new RoutesDTO();
        dto.setIdRoute(route.getIdRoute());
        dto.setQrRoute(route.getQrRoute());
        dto.setName(route.getName());
        dto.setType(route.getType());
        dto.setNum_nivel(route.getNum_nivel());
        dto.setPresa(route.getPresa());
        dto.setCreationDate(route.getCreationDate());
        return dto;
    }

    private DetailedVideoDTO convertToDetailedVideoDTO(Video video) {
        DetailedVideoDTO dto = new DetailedVideoDTO();
        dto.setId(video.getId());
        dto.setTitle(video.getTitle());
        dto.setDescription(video.getDescription());
        dto.setUrl(video.getUrl());

        return dto;
    }
}
