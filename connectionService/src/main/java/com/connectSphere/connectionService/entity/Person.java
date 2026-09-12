package com.connectSphere.connectionService.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * Represents a person entity in the Neo4j database.
 */
@Node
@Getter
@Setter
public class Person {
    /**
     * The unique identifier for the person.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The name of the person.
     */
    private String name;

    /**
     * The user ID associated with the person.
     */
    private Long userId;
}
