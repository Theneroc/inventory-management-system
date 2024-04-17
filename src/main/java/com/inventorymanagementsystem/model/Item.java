package com.inventorymanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

/**
 * Entity class for the items table in the database.
 * Contains the fields for each column in the table.
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Item")
public class Item {

    /**
     * The primary key of the item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The location of the item.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    /**
     * The name of the item.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The cost of the item.
     */
    @Column(nullable = false)
    private Double cost;

    /**
     * The markup of the item.
     */
    @Column(nullable = false)
    private Float markup;

    /**
     * The quantity of the item.
     */
    @Column(nullable = false)
    private Integer qty;

    /**
     * The expiration date of the item.
     */
    @Column
    @Temporal(TemporalType.DATE)
    private Date expiration;

}
