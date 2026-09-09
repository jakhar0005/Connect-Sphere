package com.connectSphere.userService.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * The LoginRequestDto class represents the data transfer object for user login
 * requests.
 */
@Getter
@Setter
public class LoginRequestDto {
    /**
     * The email and password of the user for login.
     */
    private String email, password;
}
