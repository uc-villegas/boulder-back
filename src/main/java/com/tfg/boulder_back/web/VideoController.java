package com.tfg.boulder_back.web;

import com.tfg.boulder_back.domain.request.AddVideoRequest;
import com.tfg.boulder_back.domain.request.UpdateVideoRequest;
import com.tfg.boulder_back.entity.Video;
import com.tfg.boulder_back.exceptions.DuplicateVideoUrlException;
import com.tfg.boulder_back.exceptions.EmailAlreadyExistsException;
import com.tfg.boulder_back.exceptions.VideoNotFoundException;
import com.tfg.boulder_back.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        } catch(IllegalArgumentException e) {
            log.error("Some required fields are empty", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (DuplicateVideoUrlException e) {
            log.error("Url already in use: " + video.getUrl(), e);
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
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

    @DeleteMapping(value = "/videos/{idVideo}")
    public ResponseEntity<Object> deleteVideo(@PathVariable Long idVideo){
        log.info("deleteRoute called");
        try{
            videoService.deleteVideo(idVideo);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/videos/{idVideo}")
    public ResponseEntity<Video> editVideo(@PathVariable Long idVideo, @RequestBody UpdateVideoRequest updateVideoRequest) {
        log.info("editVideo called");
        try {
            Video updatedVideo = videoService.editVideo(idVideo, updateVideoRequest);
            return new ResponseEntity<>(updatedVideo, HttpStatus.OK);
        } catch (VideoNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
