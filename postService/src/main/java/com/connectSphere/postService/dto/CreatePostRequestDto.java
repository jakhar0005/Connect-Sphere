package com.connectSphere.postService.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for creating a new post.
 */
@Getter
@Setter
public class CreatePostRequestDto {
    /**
     * The content of the post.
     */
    private String content;
}
