package com.connectSphere.postService.controller;

import com.connectSphere.postService.auth.AuthContextHolder;
import com.connectSphere.postService.dto.CreatePostRequestDto;
import com.connectSphere.postService.dto.CreatePostResponseDto;
import com.connectSphere.postService.service.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Controller class for handling post-related operations.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/core")
public class PostController {
    private final PostService postService;

    /**
     * Handles the creation of a new post.
     *
     * @return ResponseEntity containing the response DTO with the details of the
     * created post.
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CreatePostResponseDto> createPost(
        @RequestPart("post") final String postJson,
        @RequestPart(value = "file", required = false) final MultipartFile file) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        CreatePostRequestDto request = objectMapper.readValue(postJson, CreatePostRequestDto.class);

        final var response = postService.createPost(request, AuthContextHolder.getCurrentUserId(), file);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves a post by its ID.
     *
     * @param postId The ID of the post to retrieve.
     *
     * @return A response DTO containing the details of the retrieved post.
     */
    @GetMapping("/{postId}")
    public ResponseEntity<CreatePostResponseDto> getPost(@PathVariable final Long postId) {
        final var response = postService.getPostById(postId);

        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves all posts associated with a specific user.
     *
     * @param userId The ID of the user whose posts are to be retrieved.
     * @return A list of response DTOs containing the details of the retrieved
     * posts.
     */
    @GetMapping("/users/{userId}/allPosts")
    public ResponseEntity<List<CreatePostResponseDto>> getAllPostsOfUser(@PathVariable final Long userId) {
        final var response = postService.getAllPostsOfUser(userId);

        return ResponseEntity.ok(response);
    }

}
