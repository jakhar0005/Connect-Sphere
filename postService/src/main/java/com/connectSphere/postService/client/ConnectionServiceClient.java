package com.connectSphere.postService.client;

import com.connectSphere.postService.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="connection-service", path = "/connection/core")
public interface ConnectionServiceClient {

    @GetMapping("/{userId}/first-degree")
    public List<PersonDto> getFirstDegreeConnections(@PathVariable final Long userId);
}
