package com.connectSphere.connectionService.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for application-wide beans.
 */
@Configuration
public class AppConfig {

    /**
     * Creates and returns a ModelMapper bean for object mapping.
     *
     * @return A ModelMapper instance.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
