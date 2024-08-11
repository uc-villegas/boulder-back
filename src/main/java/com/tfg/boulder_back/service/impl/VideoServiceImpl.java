package com.tfg.boulder_back.service.impl;

import com.tfg.boulder_back.domain.request.AddVideoRequest;
import com.tfg.boulder_back.entity.Boulder;
import com.tfg.boulder_back.entity.Route;
import com.tfg.boulder_back.entity.User;
import com.tfg.boulder_back.entity.Video;
import com.tfg.boulder_back.exceptions.BoulderNotFoundException;
import com.tfg.boulder_back.exceptions.RouteNotFoundException;
import com.tfg.boulder_back.exceptions.UserNotFoundException;
import com.tfg.boulder_back.exceptions.VideoNotFoundException;
import com.tfg.boulder_back.repository.BoulderRepository;
import com.tfg.boulder_back.repository.RouteRepository;
import com.tfg.boulder_back.repository.UserRepository;
import com.tfg.boulder_back.repository.VideoRepository;
import com.tfg.boulder_back.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoulderRepository boulderRepository;

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
        videoToAdd.setDuration(videoRequest.getDuration());

        //routeRepository.save(route);
        //userRepository.save(user);
        videoRepository.save(videoToAdd);

        return videoToAdd;
    }

    @Override
    public void deleteVideo(Long idBoulder, Long idRoute, Long idVideo) {

        Optional<Boulder> optionalBoulder = boulderRepository.findById(idBoulder);

        if (optionalBoulder.isEmpty()) {
            throw new BoulderNotFoundException("Boulder not found with ID: " + idBoulder);
        }

        Optional<Route> optionalRoute = routeRepository.findById(idRoute);

        if (optionalRoute.isEmpty()) {
            throw new RouteNotFoundException("Route not found with ID: " + idRoute);
        }

        Optional<Video> optionalVideo = videoRepository.findById(idVideo);

        if(optionalVideo.isEmpty()){
            throw new VideoNotFoundException("Video not found with ID: " + idVideo);
        }

        Boulder boulder = optionalBoulder.get();
        Route route = optionalRoute.get();
        Video video = optionalVideo.get();

        if (!route.getBoulder().getIdBoulder().equals(idBoulder)) {
            throw new IllegalArgumentException("Route does not belong to Boulder with ID: " + idBoulder);
        }

        videoRepository.delete(video);
    }

    @Override
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

}
