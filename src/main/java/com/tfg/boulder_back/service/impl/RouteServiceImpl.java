package com.tfg.boulder_back.service.impl;

import com.tfg.boulder_back.domain.request.AddRouteRequest;
import com.tfg.boulder_back.dto.DetailedRouteDTO;
import com.tfg.boulder_back.dto.RoutesDTO;
import com.tfg.boulder_back.dto.UserDTO;
import com.tfg.boulder_back.dto.VideoDTO;
import com.tfg.boulder_back.entity.Boulder;
import com.tfg.boulder_back.entity.Route;
import com.tfg.boulder_back.entity.Video;
import com.tfg.boulder_back.exceptions.BoulderNotFoundException;
import com.tfg.boulder_back.exceptions.RouteNotFoundException;
import com.tfg.boulder_back.repository.BoulderRepository;
import com.tfg.boulder_back.repository.RouteRepository;
import com.tfg.boulder_back.repository.UserRepository;
import com.tfg.boulder_back.service.RouteService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private BoulderRepository boulderRepository;

    @Autowired
    private UserRepository userRepository;


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
        //route.setVideos(new HashSet<>());

        routeRepository.save(route);
        //boulder.getRoutes().add(route);
        //route.setBoulder(boulder);
        boulderRepository.save(boulder);

        return route;
    }

    @Override
    public List<RoutesDTO>  findAllRoutesById(Long idBoulder) {

        Optional<Boulder> optionalBoulder = boulderRepository.findById(idBoulder);

        if (optionalBoulder.isEmpty()) {
            throw new BoulderNotFoundException("Boulder not found with ID: " + idBoulder);
        }

        Boulder boulder = optionalBoulder.get();
        List<Route> routes = routeRepository.findAllByIdBoulder(idBoulder);

        return routes.stream().map(this::convertToRoutesDTO).collect(Collectors.toList());    }

    @Override
    public DetailedRouteDTO findByIdRouteIdBoulder(Long idBoulder, Long idRoute){

        Optional<Boulder> optionalBoulder = boulderRepository.findById(idBoulder);

        if (optionalBoulder.isEmpty()) {
            throw new BoulderNotFoundException("Boulder not found with ID: " + idBoulder);
        }

        Optional<Route> optionalRoute = routeRepository.findById(idRoute);

        if (optionalRoute.isEmpty()) {
            throw new RouteNotFoundException("Route not found with ID: " + idRoute);
        }
        
        return convertToDetailedRouteDTO(optionalRoute.get());
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

    private DetailedRouteDTO convertToDetailedRouteDTO(Route route) {
        DetailedRouteDTO dto = new DetailedRouteDTO();
        dto.setIdRoute(route.getIdRoute());
        dto.setQrRoute(route.getQrRoute());
        dto.setName(route.getName());
        dto.setType(route.getType());
        dto.setNum_nivel(route.getNum_nivel());
        dto.setPresa(route.getPresa());
        dto.setCreationDate(route.getCreationDate());
        //dto.setVideos(route.getVideos().stream().map(this::convertToVideoDTO).collect(Collectors.toList()));
        return dto;
    }

    private VideoDTO convertToVideoDTO(Video video) {
        VideoDTO dto = new VideoDTO();
        dto.setId(video.getId());
        dto.setTitle(video.getTitle());
        dto.setDescription(video.getDescription());
        dto.setUrl(video.getUrl());

        UserDTO userDTO = new UserDTO();
        userDTO.setIdUser(video.getUser().getIdUser());
        userDTO.setName(video.getUser().getName());
        dto.setUser(userDTO);

        return dto;
    }
}
