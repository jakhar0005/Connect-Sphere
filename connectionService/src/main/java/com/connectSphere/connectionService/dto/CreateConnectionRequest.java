package com.connectSphere.connectionService.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a request to create a connection between two users in the system.
 */
@Getter
@Setter
public class CreateConnectionRequest {
    /**
     * The unique identifier of the user to whom the connection request is directed.
     */
    private Long connectionUserId;
}
