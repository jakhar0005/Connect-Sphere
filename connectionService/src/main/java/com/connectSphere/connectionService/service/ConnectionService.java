package com.connectSphere.connectionService.service;

import com.connectSphere.connectionService.auth.AuthContextHolder;
import com.connectSphere.connectionService.dto.PersonDto;
import com.connectSphere.connectionService.entity.Person;
import com.connectSphere.connectionService.repository.PersonRepository;
import com.connectSphere.userService.event.CreateUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing connections between persons.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectionService {
    private final ModelMapper mapper;

    private final PersonRepository connectionRepository;

    public void createUser(final CreateUser user) {
        log.info("Creating user: {}", user);

        final var person = mapper.map(user, Person.class);

        connectionRepository.save(person);
    }

    /**
     * Fetches the first-degree connections for a given user ID.
     *
     * @param userId The ID of the user for whom to fetch first-degree
     * connections.
     *
     * @return A list of Person objects representing the first-degree
     * connections of the specified user.
     */
    public List<PersonDto> getFirstDegreeConnections(final Long userId) {
        log.info("Fetching first-degree connections for personId: {}", userId);
        final var userid = AuthContextHolder.getCurrentUserId();
        final var connections = connectionRepository.getFirstDegreeConnections(userId);

        return connections.stream()
                          .map(person -> {
                              PersonDto dto = new PersonDto();
                              dto.setId(person.getId());
                              dto.setUserId(person.getUserId());
                              dto.setName(person.getName());
                              return dto;
                          })
                          .toList();
    }

    /**
     * Establishes a connection between two users.
     *
     * @param userId The ID of the first user to initiate the connection.
     * @param connectionUserId The ID of the second user to be connected.
     */
    public void createConnection(final Long userId, final Long connectionUserId) {
        log.info(
            "Creating connection between user {} and user {}",
            userId,
            connectionUserId
                );

        connectionRepository.createConnection(
            userId,
            connectionUserId);
    }
}
