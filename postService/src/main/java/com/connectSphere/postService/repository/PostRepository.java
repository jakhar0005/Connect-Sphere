package com.connectSphere.postService.repository;

import com.connectSphere.postService.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing Post entities.
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * Finds a list of posts by the user ID.
     *
     * @param userId The ID of the user whose posts are to be retrieved.
     * @return A list of posts associated with the specified user ID.
     */
    List<Post> findByUserId(Long userId);
}
