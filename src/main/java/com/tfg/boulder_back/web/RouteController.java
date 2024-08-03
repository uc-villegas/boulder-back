package com.tfg.boulder_back.web;

import com.tfg.boulder_back.domain.request.AddRouteRequest;
import com.tfg.boulder_back.dto.DetailedRouteDTO;
import com.tfg.boulder_back.dto.RoutesDTO;
import com.tfg.boulder_back.entity.Route;
import com.tfg.boulder_back.service.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Route> addRoute(@RequestBody AddRouteRequest request) {
        try{
            log.info("addRoute called");
            return new ResponseEntity<>(routeService.addRouteAndLoadData(request), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // TODO: Cambiar el null
        }
    }

    @GetMapping(value = "/boulder/{idBoulder}/route/{idRoute}")
    public ResponseEntity<DetailedRouteDTO> getRoute(@PathVariable Long idBoulder, @PathVariable Long idRoute) {

        log.info("getRoute called");
        try {
            DetailedRouteDTO route = routeService.findByIdRouteIdBoulder(idBoulder, idRoute);
            return new ResponseEntity<>(route, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
