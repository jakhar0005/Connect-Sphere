package com.connectSphere.userService.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUser {
    private Long userId;

    private String name;
}
