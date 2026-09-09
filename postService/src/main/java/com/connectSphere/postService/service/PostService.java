package com.connectSphere.postService.service;

import com.connectSphere.postService.dto.CreatePostResponseDto;
import com.connectSphere.postService.dto.CreatePostRequestDto;
import com.connectSphere.postService.entity.Post;
import com.connectSphere.postService.exception.ResourceNotFoundException;
import com.connectSphere.postService.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing posts.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final ModelMapper mapper;

    private final PostRepository postRepository;

    /**
     * Creates a new post based on the provided request data.
     *
     * @param request The request DTO containing the data for the new post.
     *
     * @return A response DTO containing the details of the created post.
     */
    public CreatePostResponseDto createPost(final CreatePostRequestDto request
                                           , final Long userId) {
        log.info("Creating post for user with id: {}", userId);

        final var post = mapper.map(request, Post.class);

        post.setUserId(userId);

        postRepository.save(post);

        return mapper.map(post, CreatePostResponseDto.class);
    }

    /**
     * Retrieves all posts associated with a specific user.
     *
     * @param userId The ID of the user whose posts are to be retrieved.
     * @return A list of response DTOs containing the details of the retrieved
     * posts.
     */
    public List<CreatePostResponseDto> getAllPostsOfUser(final Long userId) {
        log.info("Getting all posts of user with id: {}", userId);

        final var posts = postRepository.findByUserId(userId);

        return posts.stream().map(post -> mapper
            .map(post, CreatePostResponseDto.class)).toList();
    }

    /**
     * Retrieves a post by its ID.
     *
     * @param postId The ID of the post to retrieve.
     *
     * @return A response DTO containing the details of the retrieved post.
     */
    public CreatePostResponseDto getPostById(Long postId) {
        log.info("Getting the post with ID: {}", postId);

        final var post = postRepository.findById(postId)
                                       .orElseThrow(() ->
                                                        new ResourceNotFoundException(
                                                            "Post not found with id: "
                                                                + postId));

        return mapper.map(post, CreatePostResponseDto.class);
    }
}
