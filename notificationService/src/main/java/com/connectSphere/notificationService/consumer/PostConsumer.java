package com.connectSphere.notificationService.consumer;

import com.connectSphere.notificationService.entity.Notification;
import com.connectSphere.postService.event.PostCreated;
import com.connectSphere.postService.event.PostLiked;
import com.connectSphere.notificationService.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostConsumer {
    private final NotificationService notificationService;

    @KafkaListener(topics = "post-created")
    public void handlePostCreated(final PostCreated post) {
        log.info("handlePostCreated: {}", post);

        String message = String.format("Your connection with id: %d has created this post: %s",
                                       post.getCreatorUserId(), post.getContent());

        final var notification = new Notification();
        notification.setUserId(post.getCreatorFriendUserId());
        notification.setMessage(message);

        notificationService.sendNotification(notification);
    }

    @KafkaListener(topics = "post-liked")
    public void handlePostLiked(PostLiked postLiked) {
        log.info("handlePostLiked: {}", postLiked);

        String message = String.format("User with id: %d has liked your post with id: %d",
                                       postLiked.getLikerUserId(), postLiked.getPostId());

        Notification notification = new Notification();
        notification.setUserId(postLiked.getPostCreatorUserId());
        notification.setMessage(message);

        notificationService.sendNotification(notification);
    }

}
