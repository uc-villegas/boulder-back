package com.tfg.boulder_back.service;

import com.tfg.boulder_back.dto.DetailedUserDTO;
import com.tfg.boulder_back.entity.User;

import java.util.Optional;

public interface UserService {

    User addCUser(User user);
    DetailedUserDTO getDetailedUserById(Long idUser);

}
