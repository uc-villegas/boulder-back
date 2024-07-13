package com.tfg.boulder_back.web;

import com.tfg.boulder_back.entity.Boulder;
import com.tfg.boulder_back.entity.Route;
import com.tfg.boulder_back.service.RouteService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boulder")
public class RouteController {

    private RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

//    public ResponseEntity<Route> addRoute(@RequestBody Route newRoute, Boulder boulder) {
//        try{
//            //TODO: Comprobar si existe el boulder y la ruta en el boulder
//            Route createdRoute = routeService.addRoute(newRoute);
//            return new ResponseEntity<>(createdRoute, HttpStatus.CREATED);
//        }catch(Exception e){
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
