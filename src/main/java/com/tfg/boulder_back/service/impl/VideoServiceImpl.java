package com.tfg.boulder_back.service.impl;

import com.tfg.boulder_back.domain.request.AddVideoRequest;
import com.tfg.boulder_back.entity.Route;
import com.tfg.boulder_back.entity.User;
import com.tfg.boulder_back.entity.Video;
import com.tfg.boulder_back.exceptions.RouteNotFoundException;
import com.tfg.boulder_back.exceptions.UserNotFoundException;
import com.tfg.boulder_back.exceptions.VideoNotFoundException;
import com.tfg.boulder_back.repository.RouteRepository;
import com.tfg.boulder_back.repository.UserRepository;
import com.tfg.boulder_back.repository.VideoRepository;
import com.tfg.boulder_back.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final RouteRepository routeRepository;
    private final UserRepository userRepository;

    public VideoServiceImpl(VideoRepository videoRepository, RouteRepository routeRepository, UserRepository userRepository) {
        this.videoRepository = videoRepository;
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Video addVideo(AddVideoRequest videoRequest, Long userId) {
        Optional<Route> optionalRoute = routeRepository.findById(videoRequest.getIdRoute());
        if (optionalRoute.isEmpty()) {
            throw new RouteNotFoundException("Route not found with ID: " + videoRequest.getIdRoute());
        }
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }

        Route route = optionalRoute.get();
        User user = optionalUser.get();

        Video videoToAdd = new Video();
        videoToAdd.setRoute(route);
        videoToAdd.setUser(user);

        videoToAdd.setDescription(videoRequest.getDescription());
        videoToAdd.setTitle(videoRequest.getTitle());
        videoToAdd.setUrl(videoRequest.getUrl());



        routeRepository.save(route);
        userRepository.save(user);
        videoRepository.save(videoToAdd);

        return videoToAdd;
    }

    @Override
    public Video getVideo(int id) {
        return null;
    }

    @Override
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    @Override
    public List<Video> getVideosByUser(int id) {
        return List.of();
    }

    @Override
    public Video updateVideo(Video video) {
        return null;
    }

    @Override
    public void deleteVideo(int id) {

    }
}
