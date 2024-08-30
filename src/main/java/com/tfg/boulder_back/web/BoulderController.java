package com.tfg.boulder_back.web;


import com.tfg.boulder_back.domain.request.UpdateBoulderRequest;
import com.tfg.boulder_back.domain.request.UpdateVideoRequest;
import com.tfg.boulder_back.entity.Boulder;
import com.tfg.boulder_back.entity.Video;
import com.tfg.boulder_back.exceptions.BoulderNotFoundException;
import com.tfg.boulder_back.exceptions.VideoNotFoundException;
import com.tfg.boulder_back.service.BoulderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BoulderController {

    private static final Logger log = LoggerFactory.getLogger(BoulderController.class);

    private BoulderService boulderService;

    public BoulderController(BoulderService boulderService) {
        this.boulderService = boulderService;
    }

    @GetMapping("/boulders")
    public ResponseEntity<List<Boulder>> getAllBoulders() {
        log.info("getAllBoulders");
        try{
            log.info("getAllBoulders returned");
            return new ResponseEntity<>(boulderService.findAllBoulders(), HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/boulder/enrollment")
    public ResponseEntity<Boulder> createBoulder(@RequestBody Boulder boulder) {
        log.info("createBoulder");
        try {
            log.info("Received request to create a new boulder");
            Boulder createdBoulder = boulderService.addBoulder(boulder);
            return new ResponseEntity<>(createdBoulder, HttpStatus.CREATED);
        } catch(Exception e) {
            log.error("Error while adding new boulder", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/boulder/{idBoulder}")
    public ResponseEntity<Boulder> editBoulder(@PathVariable Long idBoulder, @RequestBody UpdateBoulderRequest updateBoulderRequest) {
        log.info("editBoulder called");
        try {
            Boulder updatedBoulder = boulderService.editBoulder(idBoulder, updateBoulderRequest);
            return new ResponseEntity<>(updatedBoulder, HttpStatus.OK);
        } catch (BoulderNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
