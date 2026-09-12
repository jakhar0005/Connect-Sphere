package com.connectSphere.postService.event;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreated {
    private Long postId;

    private Long creatorUserId;

    private Long creatorFriendUserId;

    private String content;
}
