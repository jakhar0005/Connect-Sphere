package com.connectSphere.notificationService.service;

import com.connectSphere.notificationService.entity.Notification;
import com.connectSphere.notificationService.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void sendNotification(Notification notification) {
        log.info("Adding notification to db, message: {}", notification.getMessage());

        notification = notificationRepository.save(notification);
    }
}
