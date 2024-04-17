
package com.inventorymanagementsystem.model;
import jakarta.persistence.*;
import lombok.*;


/**
 * The Location class represents a location in the inventory management system.
 * It contains the location's address, email address, and phone number.
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Location")
public class Location {

    /**
     * The location's unique ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The location's physical address.
     */
    @Column(nullable = false)
    private String address;

    /**
     * The location's email address.
     */
    @Column
    private String emailAddress;

    /**
     * The location's phone number.
     */
    @Column
    private String phoneNum;
}