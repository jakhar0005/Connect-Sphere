package com.connectSphere.postService.service;

import com.connectSphere.postService.auth.AuthContextHolder;
import com.connectSphere.postService.entity.PostLike;
import com.connectSphere.postService.event.PostLiked;
import com.connectSphere.postService.exception.BadRequestException;
import com.connectSphere.postService.exception.ResourceNotFoundException;
import com.connectSphere.postService.repository.PostLikeRepository;
import com.connectSphere.postService.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing post likes.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeService {
    private final KafkaTemplate<Long, PostLiked> postLikedKafkaTemplate;

    private final ModelMapper mapper;

    private final PostLikeRepository postLikeRepository;

    private final PostRepository postRepository;

    private final PostService postService;

    /**
     * Likes a post with the given postId.
     *
     * @param postId The ID of the post to be liked.
     */
    public void likePost(final Long postId) {
        Long userId = AuthContextHolder.getCurrentUserId();

        log.info("User with ID {} liking a post with ID {}", userId, postId);

        final var post = postRepository.findById(postId)
                                       .orElseThrow(() ->
                                                        new ResourceNotFoundException(
                                                            "Post not found with ID: " + postId));

        boolean hasAlreadyLiked = postLikeRepository.existsByPostIdAndUserId(postId, userId);

        if(hasAlreadyLiked) {
            throw new BadRequestException("User with ID " + userId + " has "
                                              + "already liked the post with ID "
                                              + postId);
        }

        final var like = new PostLike();

        like.setPostId(postId);
        like.setUserId(userId);

        postLikeRepository.save(like);

        final var likedPost = postService.getPostById(postId);

        final var postLiked = PostLiked.builder()
                                       .postId(postId)
                                       .likerUserId(userId)
                                       .postCreatorUserId(post.getUserId())
                                       .build();

        postLikedKafkaTemplate.send("post-liked", postLiked);
    }

    /**
     * Unlikes a post with the given postId.
     *
     * @param postId The ID of the post to be unliked.
     */
    @Transactional
    //When you delete something from dB, dB tries to acquire lock to delete the
    // record. If the record is locked by another transaction, it will wait for
    // the lock to be released. This can lead to a deadlock if two transactions
    // are waiting for each other to release locks. To avoid this, we can use
    // @Transactional annotation to ensure that the entire method is executed
    // in a single transaction.
    public void unlikePost(final Long postId) {
        Long userId = 1L; // Replace with actual user ID retrieval logic
        log.info("User with ID {} unliking a post with ID {}", userId, postId);

        postRepository.findById(postId)
                                       .orElseThrow(() ->
                                                        new ResourceNotFoundException(
                                                            "Post not found with ID: " + postId));

        boolean hasAlreadyLiked = postLikeRepository.existsByPostIdAndUserId(postId, userId);

        if(!hasAlreadyLiked) {
            throw new BadRequestException("User with ID " + userId + " has not "
                                              + "liked the post with ID "
                                              + postId);
        }

        postLikeRepository.deleteByPostIdAndUserId(postId, userId);
    }
}
