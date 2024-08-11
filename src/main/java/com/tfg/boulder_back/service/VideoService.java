package com.tfg.boulder_back.service;

import com.tfg.boulder_back.domain.request.AddVideoRequest;
import com.tfg.boulder_back.entity.Video;

import java.util.List;

public interface VideoService {

    List<Video> getAllVideos();
    Video addVideo(AddVideoRequest video, Long userId);
    void deleteVideo(Long idBoulder, Long idRoute, Long idVideo);

}
