package com.connectSphere.connectionService.auth;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        final var userId = AuthContextHolder.getCurrentUserId();
        if (userId != null) {
            requestTemplate.header("X-User-Id", userId.toString());
        }
    }
}
