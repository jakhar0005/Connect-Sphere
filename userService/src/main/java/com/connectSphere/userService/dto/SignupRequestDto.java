package com.connectSphere.userService.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * The SignupRequestDto class represents the data transfer object for user
 * signup requests.
 */
@Getter
@Setter
public class SignupRequestDto {
    /**
     * The name, email, and password of the user to be registered.
     */
    private String name, email, password;
}
