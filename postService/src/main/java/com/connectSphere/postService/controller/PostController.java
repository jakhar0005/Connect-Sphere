package com.connectSphere.postService.controller;

import com.connectSphere.postService.dto.CreatePostResponseDto;
import com.connectSphere.postService.dto.CreatePostRequestDto;
import com.connectSphere.postService.service.PostService;
import jakarta.servlet.http.HttpServlet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * @param request The request body containing the details of the post to be
     * created.
     *
     * @return ResponseEntity containing the response DTO with the details of the
     * created post.
     */
    @PostMapping
    public ResponseEntity<CreatePostResponseDto> createPost(@RequestBody final CreatePostRequestDto request) {
        final var response = postService.createPost(request, 1L);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<CreatePostResponseDto> getPost(@PathVariable final Long postId) {
        final var response = postService.getPostById(postId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/{userId}/allPosts")
    public ResponseEntity<List<CreatePostResponseDto>> getAllPostsOfUser(@PathVariable final Long userId) {
        final var response = postService.getAllPostsOfUser(userId);

        return ResponseEntity.ok(response);
    }

}
