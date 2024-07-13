package com.tfg.boulder_back.service;

import com.tfg.boulder_back.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User addCUser(User user);
    Optional<User> findByIdUser(Long idUser);
    List<User> findAllUsers();
    User updateUser(User user);
    void deleteUser(Long idUser);

}
