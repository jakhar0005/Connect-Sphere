package com.connectSphere.userService.controller;

import com.connectSphere.userService.dto.LoginRequestDto;
import com.connectSphere.userService.dto.SignupRequestDto;
import com.connectSphere.userService.dto.UserDto;
import com.connectSphere.userService.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for user authentication and registration.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;

    /**
     * Sign up a new user.
     *
     * @param signupRequestDto The request body containing user registration
     * details.
     *
     * @return ResponseEntity containing the created UserDto and an HTTP status
     * code.
     */
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody final SignupRequestDto signupRequestDto) {
        final var userDto = authService.signUp(signupRequestDto);

        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    /**
     * Log in an existing user.
     *
     * @param loginRequestDto The request body containing user login details.
     *
     * @return ResponseEntity containing the authentication token and an HTTP
     * status code.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody final LoginRequestDto loginRequestDto) {
        final var token = authService.login(loginRequestDto);

        return ResponseEntity.ok(token);
    }

    /**
     * Get all users.
     *
     * @return All users.
     */
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(authService.getAllUsers());
    }
}
