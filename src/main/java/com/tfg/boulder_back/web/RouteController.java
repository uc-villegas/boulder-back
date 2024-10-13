package com.tfg.boulder_back.web;

import com.tfg.boulder_back.domain.request.AddRouteRequest;
import com.tfg.boulder_back.dto.DetailedRouteDTO;
import com.tfg.boulder_back.dto.RouteEditDTO;
import com.tfg.boulder_back.dto.RoutesDTO;
import com.tfg.boulder_back.dto.VideoDTO;
import com.tfg.boulder_back.entity.Route;
import com.tfg.boulder_back.entity.Video;
import com.tfg.boulder_back.exceptions.*;
import com.tfg.boulder_back.service.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RouteController {

    private static final Logger log = LoggerFactory.getLogger(RouteController.class);

    @Autowired
    private RouteService routeService;

    @GetMapping(value = "/boulder/{idBoulder}/routes")
    public ResponseEntity<List<RoutesDTO>> getRoutes(@PathVariable Long idBoulder) {
        log.info("getRoutes called");
        try{
            List<RoutesDTO> routes = routeService.findAllRoutesById(idBoulder);
            return new ResponseEntity<>(routes, HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // TODO: Cambiar el null
        }
    }

    @PostMapping(value = "/boulder/via/enrollment")
    public ResponseEntity<Object> addRoute(@RequestBody AddRouteRequest request) {
        try{
            log.info("addRoute called");
            return new ResponseEntity<>(routeService.addRouteAndLoadData(request), HttpStatus.CREATED);
        } catch (InvalidTypeException e) {
            log.error("Invalid type: " + request.getTypeRoute(), e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (InvalidLevelException e) {
            log.error("Invalid level: " + request.getNum_nivel(), e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (QrRouteAlreadyExistsException e) {
            log.error("QR already in use: " + request.getQrRoute(), e);
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } catch (RouteNameAlreadyExistsException e) {
            log.error("Name already in use: " + request.getName(), e);
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // TODO: Cambiar el null
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // TODO: Cambiar el null
        }
    }

    @GetMapping(value = "/boulder/{nameBoulder}/route/{idRoute}")
    public ResponseEntity<DetailedRouteDTO> getRoute(@PathVariable String nameBoulder, @PathVariable Long idRoute) {

        log.info("getRoute called");
        try {
            DetailedRouteDTO route = routeService.findByIdRouteNameBoulder(nameBoulder, idRoute);
            return new ResponseEntity<>(route, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/route/{idRoute}")
    public ResponseEntity<Object> deleteRoute (@PathVariable Long idRoute){
        log.info("deleteRoute called");
        try{
            routeService.deleteRoute(idRoute);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/route/{idRoute}")
    public ResponseEntity<Route> updateRoute(@PathVariable Long idRoute, @RequestBody RouteEditDTO request) {
        log.info("updateRoute called");
        try {
            Route route = routeService.updateRoute(idRoute, request);
            return new ResponseEntity<>(route, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/route/{idRoute}/videos")
    public ResponseEntity<Boolean> hasVideos(@PathVariable Long idRoute) {
        log.info("hasVideos called for route ID: " + idRoute);
        try {
            boolean hasVideos = routeService.hasVideos(idRoute);
            return new ResponseEntity<>(hasVideos, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error checking videos for route ID: " + idRoute, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
