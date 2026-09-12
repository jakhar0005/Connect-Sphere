package com.connectSphere.connectionService.controller;

import com.connectSphere.connectionService.auth.AuthContextHolder;
import com.connectSphere.connectionService.dto.CreateConnectionRequest;
import com.connectSphere.connectionService.dto.PersonDto;
import com.connectSphere.connectionService.service.ConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Manges connections between users.
 */
@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class ConnectionController {
    private final ConnectionService connectionService;

    /**
     * Fetches a list of first-degree connections for a given user.
     *
     * @param userId The ID of the user for whom the first-degree connections are to be retrieved.
     * @return A ResponseEntity object containing a list of PersonDto objects representing
     *         the first-degree connections of the specified user.
     */
    @GetMapping("/{userId}/first-degree")
    public ResponseEntity<List<PersonDto>> getFirstDegreeConnections(@PathVariable final Long userId) {
        final var connections = connectionService.getFirstDegreeConnections(userId);

        return ResponseEntity.ok(connections);
    }

    /**
     * Establishes a connection between two users.
     */
    @PostMapping("/create-connection")
    public ResponseEntity<Void> createConnection(@RequestBody final CreateConnectionRequest request) {
        connectionService.createConnection(AuthContextHolder.getCurrentUserId(), request.getConnectionUserId());

        return ResponseEntity.ok().build();
    }
}
