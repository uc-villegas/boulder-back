package com.tfg.boulder_back.web;

import com.tfg.boulder_back.domain.request.AddRouteRequest;
import com.tfg.boulder_back.entity.Route;
import com.tfg.boulder_back.service.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RouteController {

    private static final Logger log = LoggerFactory.getLogger(RouteController.class);

    private RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping(value = "/v1/{idBoulder}/vias")
    public ResponseEntity<List<Route>> getRoutes(@PathVariable Long idBoulder) {
        log.info("getRoutes called");
        try{
            log.info("getRoutes called");
            return new ResponseEntity<>(routeService.findAllRoutesById(idBoulder), HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // TODO: Cambiar el null
        }
    }

    @PostMapping(value = "/v1/boulder/via/enrollment")
    public ResponseEntity<Route> addRoute(@RequestBody AddRouteRequest request) {
        try{
            log.info("addRoute called");
            return new ResponseEntity<>(routeService.addRouteAndLoadData(request), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // TODO: Cambiar el null
        }
    }

    @GetMapping(value = "/v1/{idBoulder}/{idRoute}")
    public ResponseEntity<Route> getRoute(@PathVariable Long idBoulder, @PathVariable Long idRoute) {
        try {
            log.info("getRoute called");
            return new ResponseEntity<>(routeService.findByIdRouteIdBoulder(idBoulder, idRoute), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
