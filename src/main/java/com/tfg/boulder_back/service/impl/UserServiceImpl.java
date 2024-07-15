package com.tfg.boulder_back.service.impl;

import com.tfg.boulder_back.entity.User;
import com.tfg.boulder_back.repository.UserRepository;
import com.tfg.boulder_back.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addCUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByIdUser(Long idUser) {
        return Optional.empty();
    }

    @Override
    public List<User> findAllUsers() {
        return List.of();
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void deleteUser(Long idUser) {

    }
}
