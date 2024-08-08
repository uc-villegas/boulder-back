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

    @PostMapping(value = "/boulder/via/video/add")
    public ResponseEntity<Video> addVideo(@RequestBody AddVideoRequest video, @RequestParam Long userId) {
        log.info("Adding video: {}", video.getUrl());
        try{
            return new ResponseEntity<>(videoService.addVideo(video, userId), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/videos")
    public ResponseEntity<List<Video>> getAllVideos() {
        log.info("Getting all videos");
        return new ResponseEntity<>(videoService.getAllVideos(), HttpStatus.OK);
    }
}
