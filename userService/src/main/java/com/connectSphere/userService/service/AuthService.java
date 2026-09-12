package com.connectSphere.userService.service;

import com.connectSphere.userService.dto.LoginRequestDto;
import com.connectSphere.userService.dto.SignupRequestDto;
import com.connectSphere.userService.dto.UserDto;
import com.connectSphere.userService.entity.User;
import com.connectSphere.userService.event.CreateUser;
import com.connectSphere.userService.exception.BadRequestException;
import com.connectSphere.userService.exception.ResourceNotFoundException;
import com.connectSphere.userService.repository.UserRepository;
import com.connectSphere.userService.utils.BCrypt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The AuthService class provides authentication-related services such as user
 * login and signup.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final JwtService jwtService;

    private final KafkaTemplate<String, CreateUser> kafkaTemplate;

    private final ModelMapper mapper;

    private final UserRepository userRepository;

    /**
     * Get all users.
     *
     * @return All users.
     */
    public List<UserDto> getAllUsers() {
        final var users = userRepository.findAll();

        return users.stream().map(user -> mapper.map(user, UserDto.class)).toList();
    }

    /**
     * Authenticates a user based on the provided login request data.
     *
     * @param request The login request data transfer object containing user
     * credentials.
     *
     * @return A JWT access token if the authentication is successful.
     */
    public String login(final LoginRequestDto request) {
        log.info("Login request received for email: {}", request.getEmail());

        final var user = userRepository.findByEmail(request.getEmail())
                                       .orElseThrow(() -> new ResourceNotFoundException(
                                           "Incorrect email or password"));


        final var authenticated = BCrypt.checkPassword(request.getPassword(), user.getPassword());

        if(!authenticated) {
            throw new BadRequestException("Incorrect email or password");
        }

        return jwtService.generateAccessToken(user);
    }

    /**
     * Signs up a new user based on the provided signup request data transfer
     * object (DTO).
     *
     * @param request The signup request data transfer object
     * containing user information.
     *
     * @return A UserDto representing the newly created user.
     */
    public UserDto signUp(final SignupRequestDto request) {
        log.info("Signup request received for email: {}", request.getEmail());

        final var existingUser = userRepository.existsByEmail(request.getEmail());

        if (existingUser) {
            throw new BadRequestException("User is already registered with "
                                              + "email id:" + request.getEmail());
        }

        var user = mapper.map(request, User.class);

        user.setPassword(BCrypt.hashPassword(request.getPassword()));

        user = userRepository.save(user);

        final var createUser = new CreateUser();
        createUser.setUserId(user.getId());
        createUser.setName(user.getName());

        kafkaTemplate.send("user-created", createUser);

        return mapper.map(user, UserDto.class);
    }
}
