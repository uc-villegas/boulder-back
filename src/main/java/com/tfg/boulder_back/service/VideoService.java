package com.tfg.boulder_back.service;

import com.tfg.boulder_back.domain.request.AddVideoRequest;
import com.tfg.boulder_back.entity.User;
import com.tfg.boulder_back.entity.Video;

import java.util.List;

public interface VideoService {

    Video getVideo(int id);
    List<Video> getAllVideos();
    List<Video> getVideosByUser(int id);
    Video addVideo(AddVideoRequest video, Long userId);
    Video updateVideo(Video video);
    void deleteVideo(int id);

}
