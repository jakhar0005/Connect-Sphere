package com.connectSphere.connectionService.repository;

import com.connectSphere.connectionService.entity.Person;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing Person entities.
 */
public interface PersonRepository extends Neo4jRepository<Person, Long> {
    /**
     * Finds a Person entity by its userId.
     *
     * @param userId The userId of the Person to find.
     *
     * @return An Optional containing the found Person, or empty if not found.
     */
    Optional<Person> findByUserId(Long userId);

    /**
     * Retrieves the first-degree connections of a Person based on their userId.
     *
     * @param userId The userId of the Person whose connections are to be
     * retrieved.
     *
     * @return A list of Person entities representing the first-degree
     * connections.
     */
    @Query("match (personA:Person)-[:CONNECTED_TO]-(personB:Person) "
        + "where personA.userId = $userId "
        + "return personB")
    List<Person> getFirstDegreeConnections(Long userId);

    /**
     * For now to connect first run below query in neo4j.
     * CREATE
     *     (a:Person {userId: 1, name: "AARKJ"}),
     *     (b:Person {userId: 2, name: "AR"}),
     *     (c:Person {userId: 3, name: "AK"});
     */
    /**
     * Creates a CONNECTION relationship between two Person nodes in the graph.
     * If a relationship already exists, it will not create a duplicate.
     *
     * @param userId The userId of the first Person node to connect.
     * @param connectionUserId The userId of the second Person node to connect.
     */
    @Query("""
    MATCH (personA:Person {userId: $userId})
    MATCH (personB:Person {userId: $connectionUserId})
    MERGE (personA)-[:CONNECTED_TO]-(personB)
    RETURN personA
    """)
    Person createConnection(Long userId, Long connectionUserId);
}
