package com.tfg.boulder_back.service;

import com.tfg.boulder_back.dto.DetailedUserDTO;
import com.tfg.boulder_back.entity.User;

public interface UserService {

    User addUser(User user);
    DetailedUserDTO getDetailedUserById(Long idUser);

}
