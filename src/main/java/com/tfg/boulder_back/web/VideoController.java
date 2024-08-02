package com.tfg.boulder_back.web;

import com.tfg.boulder_back.domain.request.AddVideoRequest;
import com.tfg.boulder_back.entity.Video;
import com.tfg.boulder_back.service.RouteService;
import com.tfg.boulder_back.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VideoController {

    private static final Logger log = LoggerFactory.getLogger(VideoController.class);

    private VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping(value = "v1/boulder/via/video/add")
    public ResponseEntity<Video> addVideo(@RequestBody AddVideoRequest video, @RequestParam Long userId) {
        log.info("Adding video: {}", video.getUrl());
        try{
            return new ResponseEntity<>(videoService.addVideo(video, userId), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "v1/videos")
    public ResponseEntity<List<Video>> getAllVideos() {
        log.info("Getting all videos");
        return new ResponseEntity<>(videoService.getAllVideos(), HttpStatus.OK);
    }
}
