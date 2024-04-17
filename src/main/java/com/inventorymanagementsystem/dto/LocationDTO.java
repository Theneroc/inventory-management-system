package com.inventorymanagementsystem.dto;

import lombok.Data;

/**
 * Data Transfer Object for Location
 */
@Data
public class LocationDTO {

    /**
     * Unique identifier for the location
     */
    private Long id;

    /**
     * Street address of the location
     */
    private String address;

    /**
     * Email address of the location
     */
    private String emailAddress;

    /**
     * Phone number of the location
     */
    private String phoneNum;
}
