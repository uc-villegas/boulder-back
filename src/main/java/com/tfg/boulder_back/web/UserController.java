package com.tfg.boulder_back.web;

import com.tfg.boulder_back.dto.DetailedUserDTO;
import com.tfg.boulder_back.entity.User;
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
            User createdUser = userService.addCUser(newUser);
            log.info("Received request to create a new customer");
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
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
}
