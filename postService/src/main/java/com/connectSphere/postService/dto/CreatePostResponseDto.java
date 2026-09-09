package com.connectSphere.postService.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * DTO for creating a post response.
 */
@Getter
@Setter
public class CreatePostResponseDto {
    /**
     * The unique ID of the created post.
     */
    private Long id;

    /**
     * The content of the created post.
     */
    private String content;

    /**
     * The ID of the user who created the post.
     */
    private Long userId;

    /**
     * The timestamp when the post was created.
     */
    private LocalDateTime createdAt;
}
