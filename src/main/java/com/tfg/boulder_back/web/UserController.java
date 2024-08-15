package com.tfg.boulder_back.web;

import com.tfg.boulder_back.domain.request.LoginRequest;
import com.tfg.boulder_back.dto.DetailedUserDTO;
import com.tfg.boulder_back.dto.UserHomeDTO;
import com.tfg.boulder_back.entity.User;
import com.tfg.boulder_back.exceptions.AuthenticationException;
import com.tfg.boulder_back.exceptions.EmailAlreadyExistsException;
import com.tfg.boulder_back.exceptions.UserNotFoundException;
import com.tfg.boulder_back.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/user/enrollment")
    public ResponseEntity<User> addUser(@RequestBody User newUser){
        try {
            User createdUser = userService.addUser(newUser);
            log.info("Received request to create a new customer");
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (EmailAlreadyExistsException e) {
            log.error("Email already in use: " + newUser.getEmail(), e);
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } catch(Exception e) {
            log.error("Error while adding new user", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<DetailedUserDTO> getUserById(@PathVariable Long id) {
        try {
            DetailedUserDTO user = userService.getDetailedUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try {
            UserHomeDTO user = userService.authenticateUser(loginRequest);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
