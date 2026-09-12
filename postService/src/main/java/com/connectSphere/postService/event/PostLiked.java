package com.connectSphere.postService.event;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PostLiked {
    private Long postId;
    private Long likerUserId;
    private Long postCreatorUserId;
}
