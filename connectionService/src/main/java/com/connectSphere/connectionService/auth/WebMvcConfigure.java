package com.connectSphere.connectionService.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This configuration integrates the {@link RequestInterceptor} into the interceptor
 * chain, allowing custom pre- and post-processing of HTTP requests and responses.
 */
@Configuration
public class WebMvcConfigure implements WebMvcConfigurer {
    @Autowired
    private RequestInterceptor requestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor);

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
