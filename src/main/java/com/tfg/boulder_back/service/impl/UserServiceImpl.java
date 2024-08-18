package com.tfg.boulder_back.service.impl;

import com.tfg.boulder_back.constants.TypeUser;
import com.tfg.boulder_back.domain.request.LoginRequest;
import com.tfg.boulder_back.dto.DetailedUserDTO;
import com.tfg.boulder_back.dto.DetailedVideoDTO;
import com.tfg.boulder_back.dto.UserHomeDTO;
import com.tfg.boulder_back.entity.User;
import com.tfg.boulder_back.entity.Video;
import com.tfg.boulder_back.exceptions.AuthenticationException;
import com.tfg.boulder_back.exceptions.BoulderPropertyNotFoundException;
import com.tfg.boulder_back.exceptions.EmailAlreadyExistsException;
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
    public User addUser(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email " + user.getEmail() + " is already in use.");
        }

        if (user.getRole() == null) {
            user.setRole(TypeUser.USER);
        }else if (user.getRole() == TypeUser.WORKER) {
            if(user.getBoulder() == null){
                throw new BoulderPropertyNotFoundException("Boulder is null");
            }
        }


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

    @Override
    public UserHomeDTO authenticateUser(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());

        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User not found");
        }

        User user = userOptional.get();
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new AuthenticationException("Invalid credentials");
        }

        return convertToUserHomeDTO(user);
    }

    private UserHomeDTO convertToUserHomeDTO(User user){
        UserHomeDTO userHomeDTO = new UserHomeDTO();
        userHomeDTO.setIdUser(user.getIdUser());
        userHomeDTO.setName(user.getName());
        userHomeDTO.setSurname(user.getSurname());
        userHomeDTO.setEmail(user.getEmail());
        userHomeDTO.setRole(user.getRole());
        userHomeDTO.setBoulder(user.getBoulder());

        return userHomeDTO;
    }

    private DetailedVideoDTO convertToDetailedVideoDTO(Video video) {
        DetailedVideoDTO dto = new DetailedVideoDTO();
        dto.setId(video.getId());
        dto.setTitle(video.getTitle());
        dto.setDescription(video.getDescription());
        dto.setUrl(video.getUrl());
        dto.setDuration(video.getDuration());

        return dto;
    }
}
