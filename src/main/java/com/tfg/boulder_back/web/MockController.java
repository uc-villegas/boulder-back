package com.tfg.boulder_back.web;

import com.tfg.boulder_back.domain.request.AddUserRequest;
import com.tfg.boulder_back.domain.response.BoulderDetails;
import com.tfg.boulder_back.util.MockData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MockController {

    private static final Logger log = LoggerFactory.getLogger(MockController.class);

    @PostMapping("/v1/mock/user/enrollment")
    public ResponseEntity<String> addUser(AddUserRequest addUserRequest){
        log.info("User registered successfully with ID: {}", addUserRequest.getIdUser());
        return ResponseEntity.ok().body("User enrolled successfully with userId: " + addUserRequest.getIdUser());
    }

    @GetMapping("/v1/mock/boulder/info/{idBoulder}")
    public ResponseEntity<BoulderDetails> getBoulderDetails(@PathVariable("idBoulder") String idBoulder){
        log.info("Received request to get boulder info: {}", idBoulder);
        return ResponseEntity.ok(MockData.getBoulderData());
    }

    @GetMapping("/v1/mock/boulder/info")
    public ResponseEntity<BoulderDetails> getBoulders(){
        log.info("Received request to get all the boulders registered");
        return ResponseEntity.ok(MockData.getBoulderData()); //TODO Temporalmente solo devuelve 1 para probar con movil
    }
}
