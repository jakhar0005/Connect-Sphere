package com.connectSphere.postService.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * A data transfer object for a person.
 */
@Getter
@Setter
public class PersonDto {
    /**
     * The unique identifier of the person.
     */
    private Long id;

    /**
     * The unique identifier of the user associated with the person.
     */
    private Long userId;

    /**
     * The name of the person.
     */
    private String name;
}
