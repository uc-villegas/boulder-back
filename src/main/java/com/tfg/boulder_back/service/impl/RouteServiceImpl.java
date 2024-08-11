package com.tfg.boulder_back.service.impl;

import com.tfg.boulder_back.domain.request.AddRouteRequest;
import com.tfg.boulder_back.dto.*;
import com.tfg.boulder_back.entity.Boulder;
import com.tfg.boulder_back.entity.Route;
import com.tfg.boulder_back.entity.User;
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
        route.setTypeRoute(routeRequest.getTypeRoute());
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
    public DetailedRouteDTO findByIdRouteNameBoulder(String nameBoulder, Long idRoute){

        DetailedRouteDTO detailedRouteDTO = new DetailedRouteDTO();

        Optional<Boulder> optionalBoulder = boulderRepository.findByName(nameBoulder);
        if (optionalBoulder.isEmpty()) {
            throw new BoulderNotFoundException("Boulder not found with the name: " + nameBoulder);
        }

        Optional<Route> optionalRoute = routeRepository.findById(idRoute);

        if (optionalRoute.isEmpty()) {
            throw new RouteNotFoundException("Route not found with ID: " + idRoute);
        }

        DetailedBoulderDTO boulder = converToDetailedBoulderDTO(optionalBoulder.get());

        Route route = optionalRoute.get();
        List<Video> videos = videoRepository.findByRouteId(idRoute); // TODO: revisar repository, aqui se busca por idRoute, en UserServiceImpl se busca por idUser.

        detailedRouteDTO.setIdRoute(route.getId());
        detailedRouteDTO.setName(route.getName());
        detailedRouteDTO.setQrRoute(route.getQrRoute());
        detailedRouteDTO.setCreationDate(route.getCreationDate());
        detailedRouteDTO.setPresa(route.getPresa());
        detailedRouteDTO.setTypeRoute(route.getTypeRoute());
        detailedRouteDTO.setNum_nivel(route.getNum_nivel());

        detailedRouteDTO.setBoulder(boulder);

        List<VideoDTO> videosDTO = videos.stream().map(this::convertToVideoDTO).toList();
        detailedRouteDTO.setVideos(videosDTO);

        return detailedRouteDTO;
    }

//    @Override
//    public Route updateRoute(Long idBoulder, Long idRoute, UpdateRouteRequest request) {
//        return null;
//    }

    @Override
    public void deleteRoute(Long idBoulder, Long idRoute) {

        Optional<Boulder> optionalBoulder = boulderRepository.findById(idBoulder);

        if (optionalBoulder.isEmpty()) {
            throw new BoulderNotFoundException("Boulder not found with ID: " + idBoulder);
        }

        Boulder boulder = optionalBoulder.get();

        Optional<Route> optionalRoute = routeRepository.findById(idRoute);

        if (optionalRoute.isEmpty()) {
            throw new RouteNotFoundException("Route not found with ID: " + idRoute);
        }

        Route route = optionalRoute.get();

        if (!route.getBoulder().getIdBoulder().equals(idBoulder)) {
            throw new IllegalArgumentException("Route does not belong to Boulder with ID: " + idBoulder);
        }

        boulder.getRoutes().remove(route);
        routeRepository.delete(route);
        boulderRepository.save(boulder);

    }


    private RoutesDTO convertToRoutesDTO(Route route) {
        RoutesDTO dto = new RoutesDTO();
        dto.setIdRoute(route.getId());
        dto.setQrRoute(route.getQrRoute());
        dto.setName(route.getName());
        dto.setTypeRoute(route.getTypeRoute());
        dto.setNum_nivel(route.getNum_nivel());
        dto.setPresa(route.getPresa());
        dto.setCreationDate(route.getCreationDate());
        return dto;
    }

    private VideoDTO convertToVideoDTO(Video video) {
        VideoDTO dto = new VideoDTO();
        dto.setId(video.getId());
        dto.setTitle(video.getTitle());
        dto.setDescription(video.getDescription());
        dto.setUrl(video.getUrl());
        dto.setDuration(video.getDuration());
        dto.setUser(convertToUserDTO(video.getUser()));

        return dto;
    }

    private UserDTO convertToUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setIdUser(user.getIdUser());
        dto.setName(user.getName());

        return dto;
    }

    private DetailedBoulderDTO converToDetailedBoulderDTO(Boulder boulder){
        DetailedBoulderDTO dto = new DetailedBoulderDTO();
        dto.setName(boulder.getName());
        dto.setAddress(boulder.getAddress());
        dto.setLocality(boulder.getLocality());
        dto.setMail(boulder.getMail());
        dto.setPhone(boulder.getPhone());
        dto.setIdBoulder(boulder.getIdBoulder());

        return dto;
    }
}
