package com.tfg.boulder_back.service;

import com.tfg.boulder_back.domain.request.LoginRequest;
import com.tfg.boulder_back.dto.DetailedUserDTO;
import com.tfg.boulder_back.dto.UserHomeDTO;
import com.tfg.boulder_back.entity.User;

public interface UserService {

    User addUser(User user);
    DetailedUserDTO getDetailedUserById(Long idUser);
    UserHomeDTO authenticateUser(LoginRequest loginRequest);

}
