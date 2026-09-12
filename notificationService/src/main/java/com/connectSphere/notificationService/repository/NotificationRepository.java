package com.connectSphere.notificationService.repository;

import com.connectSphere.notificationService.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
