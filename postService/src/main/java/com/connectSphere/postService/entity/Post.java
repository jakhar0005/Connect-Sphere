package com.connectSphere.postService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * The post entity in the application.
 */
@Entity
@Getter
@Setter
public class Post {
    /**
     * The unique identifier for the post.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The content of the post.
     */
    @Column(nullable = false)
    private String content;

    /**
     * The unique identifier of the user who created the post.
     */
    @Column(nullable = false)
    private Long userId;

    /**
     * The timestamp when the post was created.
     */
    @CreationTimestamp
    private LocalDateTime createdAt;
}
