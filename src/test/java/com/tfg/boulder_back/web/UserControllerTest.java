package com.tfg.boulder_back.web;

import com.tfg.boulder_back.constants.TypeUser;
import com.tfg.boulder_back.domain.request.LoginRequest;
import com.tfg.boulder_back.dto.DetailedUserDTO;
import com.tfg.boulder_back.dto.UserHomeDTO;
import com.tfg.boulder_back.entity.User;
import com.tfg.boulder_back.exceptions.AuthenticationException;
import com.tfg.boulder_back.exceptions.EmailAlreadyExistsException;
import com.tfg.boulder_back.exceptions.UserNotFoundException;
import com.tfg.boulder_back.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void userCreation_201_created(){

        User newUser = new User(null, "John", "Doe", "john.doe@example.com", "password123", TypeUser.USER, null);
        User createdUser = new User(1L, "John", "Doe", "john.doe@example.com", "password123", TypeUser.USER, null);

        Mockito.when(userService.addUser(Mockito.any())).thenReturn(createdUser);

        ResponseEntity<User> response = userController.addUser(newUser);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdUser, response.getBody());
    }

    @Test
    void userCreation_409_conflict() {
        User newUser = new User(null, "Jane", "Doe", "jane.doe@example.com", "password456", TypeUser.USER, null);

        Mockito.when(userService.addUser(Mockito.any())).thenThrow(new EmailAlreadyExistsException("Email already exists"));

        ResponseEntity<User> response = userController.addUser(newUser);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

        assertNull(response.getBody());
    }

    @Test
    void userCreation_500_internalServerError() {

        User newUser = new User(null, "Sam", "Smith", "sam.smith@example.com", "password789", TypeUser.USER, null);

        Mockito.when(userService.addUser(Mockito.any())).thenThrow(new RuntimeException("Unexpected error"));

        ResponseEntity<User> response = userController.addUser(newUser);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        assertNull(response.getBody());
    }

    @Test
    void getUser_200_ok() {

        DetailedUserDTO userDTO = new DetailedUserDTO(1L, "John", "Doe", "john.doe@example.com", null);

        Mockito.when(userService.getDetailedUserById(Mockito.anyLong())).thenReturn(userDTO);

        ResponseEntity<DetailedUserDTO> response = userController.getUserById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(userDTO, response.getBody());
    }

    @Test
    void getUser_404_notFound() {
        Mockito.when(userService.getDetailedUserById(Mockito.anyLong())).thenThrow(new UserNotFoundException("User not found"));

        ResponseEntity<DetailedUserDTO> response = userController.getUserById(999L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        assertNull(response.getBody());
    }

    @Test
    void login_200_ok() {
        LoginRequest loginRequest = new LoginRequest("john.doe@example.com", "password123");
        UserHomeDTO userHomeDTO = new UserHomeDTO(1L, "John", "Doe", "john.doe@example.com", TypeUser.USER, null);

        Mockito.when(userService.authenticateUser(Mockito.any())).thenReturn(userHomeDTO);

        ResponseEntity<?> response = userController.login(loginRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(userHomeDTO, response.getBody());
    }

    @Test
    void login_404_notFound() {
        LoginRequest loginRequest = new LoginRequest("jane.doe@example.com", "wrongpassword");

        Mockito.when(userService.authenticateUser(Mockito.any())).thenThrow(new UserNotFoundException("User not found"));

        ResponseEntity<?> response = userController.login(loginRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        assertEquals("User not found", response.getBody());
    }

    @Test
    void login_401_invalidCredentials() {
        LoginRequest loginRequest = new LoginRequest("john.doe@example.com", "wrongpassword");

        Mockito.when(userService.authenticateUser(Mockito.any())).thenThrow(new AuthenticationException("Invalid credentials"));

        ResponseEntity<?> response = userController.login(loginRequest);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

        assertEquals("Invalid credentials", response.getBody());
    }

    @Test
    void login_500_internalServerError() {
        LoginRequest loginRequest = new LoginRequest("john.doe@example.com", "password123");

        Mockito.when(userService.authenticateUser(Mockito.any())).thenThrow(new RuntimeException("Unexpected error"));

        ResponseEntity<?> response = userController.login(loginRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        assertEquals("An error occurred", response.getBody());
    }
}
