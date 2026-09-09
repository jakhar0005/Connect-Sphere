package com.connectSphere.postService.controller;

import com.connectSphere.postService.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing post likes.
 */
@RequestMapping("/likes")
@RestController
@RequiredArgsConstructor
public class PostLikesController {
    private final PostLikeService postLikeService;

    /**
     * Like a post by its ID.
     *
     * @param postId The ID of the post to be liked.
     *
     * @return ResponseEntity with no content (HTTP 204) if the operation is
     * successful.
     */
    @PostMapping("/{postId}")
    public ResponseEntity<Void> likePost(@PathVariable final Long postId) {
        postLikeService.likePost(postId);

        return ResponseEntity.noContent().build();
    }

    /**
     * Unlike a post by its ID.
     *
     * @param postId The ID of the post to be unliked.
     *
     * @return ResponseEntity with no content (HTTP 204) if the operation is
     * successful.
     */
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> unlikePost(@PathVariable final Long postId) {
        postLikeService.unlikePost(postId);

        return ResponseEntity.noContent().build();
    }
}
