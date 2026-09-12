package com.connectSphere.connectionService.consumer;

import com.connectSphere.connectionService.service.ConnectionService;
import com.connectSphere.userService.event.CreateUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserConsumer {
    private final ConnectionService connectionService;

    @KafkaListener(topics = "user-created")
    public void createUser(final CreateUser user) {
        log.info("Received user: {}", user);

        connectionService.createUser(user);
    }
}
