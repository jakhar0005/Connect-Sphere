package com.connectSphere.postService.repository;

import com.connectSphere.postService.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing PostLike entities.
 */
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    /**
     * Checks if a like exists for a given post and user.
     *
     * @param postId The ID of the post.
     * @param userId The ID of the user.
     *
     * @return {@code true} if a like exists for the given post and user,
     * {@code false} otherwise.
     */
    boolean existsByPostIdAndUserId(Long postId, Long userId);

    /**
     * Delete a like for a given post and user.
     *
     * @param postId The ID of the post.
     * @param userId The ID of the user.
     */
    void deleteByPostIdAndUserId(Long postId, Long userId);
}
