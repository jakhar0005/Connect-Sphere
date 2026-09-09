package com.connectSphere.userService.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * A Data Transfer Object (DTO) representing a user in the system.
 */
@Getter
@Setter
public class UserDto {
    /**
     * The unique identifier of the user.
     */
    private Long id;

    /**
     * The name and email of the user.
     */
    private String name, email;
}
