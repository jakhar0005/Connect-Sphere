package com.connectSphere.userService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the user.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The email address of the user, which must be unique.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * The password of the user, which is stored in a hashed format for security.
     */
    @Column(nullable = false)
    private String password;
}

