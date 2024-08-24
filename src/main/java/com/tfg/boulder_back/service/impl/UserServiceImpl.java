package com.tfg.boulder_back.service.impl;

import com.tfg.boulder_back.constants.TypeUser;
import com.tfg.boulder_back.domain.request.LoginRequest;
import com.tfg.boulder_back.dto.DetailedBoulderDTO;
import com.tfg.boulder_back.dto.DetailedUserDTO;
import com.tfg.boulder_back.dto.DetailedVideoDTO;
import com.tfg.boulder_back.dto.UserHomeDTO;
import com.tfg.boulder_back.entity.Boulder;
import com.tfg.boulder_back.entity.User;
import com.tfg.boulder_back.entity.Video;
import com.tfg.boulder_back.exceptions.AuthenticationException;
import com.tfg.boulder_back.exceptions.BoulderPropertyNotFoundException;
import com.tfg.boulder_back.exceptions.EmailAlreadyExistsException;
import com.tfg.boulder_back.exceptions.UserNotFoundException;
import com.tfg.boulder_back.repository.BoulderRepository;
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
    @Autowired
    private BoulderRepository boulderRepository;

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

        detailedUserDTO.setIdUser(user.getId());
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
        userHomeDTO.setIdUser(user.getId());
        userHomeDTO.setName(user.getName());
        userHomeDTO.setSurname(user.getSurname());
        userHomeDTO.setEmail(user.getEmail());
        userHomeDTO.setRole(user.getRole());

        if(user.getRole() == TypeUser.WORKER){

            Optional<Boulder> boulderOptional = boulderRepository.findById(user.getBoulder());

            if(boulderOptional.isPresent()){
                Boulder boulder = boulderOptional.get();

                DetailedBoulderDTO detailedBoulderDTO = new DetailedBoulderDTO();
                detailedBoulderDTO.setIdBoulder(boulder.getIdBoulder());
                detailedBoulderDTO.setName(boulder.getName());
                detailedBoulderDTO.setLocality(boulder.getLocality());
                detailedBoulderDTO.setAddress(boulder.getAddress());
                detailedBoulderDTO.setMail(boulder.getMail());
                detailedBoulderDTO.setPhone(boulder.getPhone());

                userHomeDTO.setBoulder(detailedBoulderDTO);
            }

        }

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
