package com.tfg.boulder_back.service.impl;

import com.tfg.boulder_back.dto.DetailedUserDTO;
import com.tfg.boulder_back.dto.DetailedVideoDTO;
import com.tfg.boulder_back.entity.User;
import com.tfg.boulder_back.entity.Video;
import com.tfg.boulder_back.exceptions.UserNotFoundException;
import com.tfg.boulder_back.repository.UserRepository;
import com.tfg.boulder_back.repository.VideoRepository;
import com.tfg.boulder_back.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VideoRepository videoRepository;

    @Override
    public User addCUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public DetailedUserDTO getDetailedUserById(Long id) {

        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User not found");
        }

        DetailedUserDTO detailedUserDTO = new DetailedUserDTO();

        User user = userOptional.get();
        List<Video> videos = videoRepository.findAllById(Collections.singleton(id)); //TODO idUser o idRoute?

        detailedUserDTO.setIdUser(user.getIdUser());
        detailedUserDTO.setName(user.getName());
        detailedUserDTO.setSurname(user.getSurname());
        detailedUserDTO.setEmail(user.getEmail());

        List<DetailedVideoDTO> detailedVideosDTO = videos.stream().map(this::convertToDetailedVideoDTO).toList();
        detailedUserDTO.setVideos(detailedVideosDTO);

        return detailedUserDTO;
    }

    private DetailedVideoDTO convertToDetailedVideoDTO(Video video) {
        DetailedVideoDTO dto = new DetailedVideoDTO();
        dto.setId(video.getId());
        dto.setTitle(video.getTitle());
        dto.setDescription(video.getDescription());
        dto.setUrl(video.getUrl());

        return dto;
    }
}
