package com.tfg.boulder_back.web;

import com.tfg.boulder_back.domain.request.AddVideoRequest;
import com.tfg.boulder_back.entity.Video;
import com.tfg.boulder_back.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class VideoController {

    private static final Logger log = LoggerFactory.getLogger(VideoController.class);

    @Autowired
    private VideoService videoService;

    @PostMapping(value = "/user/{userId}/boulder/{boulderName}/via/{routeName}/video/add")
    public ResponseEntity<Video> addVideo(@RequestBody AddVideoRequest video, @PathVariable Long userId, @PathVariable String boulderName, @PathVariable String routeName) {
        log.info("Adding video: {}", video.getUrl());
        try{
            return new ResponseEntity<>(videoService.addVideo(video, userId, boulderName, routeName), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/videos")
    public ResponseEntity<List<Video>> getAllVideos() {
        log.info("Getting all videos");
        return new ResponseEntity<>(videoService.getAllVideos(), HttpStatus.OK);
    }

    @GetMapping(value = "/user/{idUser}/videos")
    public ResponseEntity<List<Video>> getAllVideosByUser(@PathVariable Long idUser) {
        log.info("Getting all videos");
        return new ResponseEntity<>(videoService.getVideosByUserId(idUser), HttpStatus.OK);
    }

    @DeleteMapping(value = "/boulder/{idBoulder}/route/{idRoute}/videos/{idVideo}")
    public ResponseEntity<Object> deleteVideo(@PathVariable Long idBoulder, @PathVariable Long idRoute, @PathVariable Long idVideo){
        log.info("deleteRoute called");
        try{
            videoService.deleteVideo(idBoulder, idRoute, idVideo);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
