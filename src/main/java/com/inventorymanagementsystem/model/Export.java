package com.inventorymanagementsystem.model;

import lombok.*;

import jakarta.persistence.*;
import java.sql.Date;

/**
 * The Export class represents an export transaction in the inventory management system.
 * It contains information about the item being exported, the location where it is being sent,
 * the income generated from the export, the quantity exported, and the date of the transaction.
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Export")
public class Export {

    /**
     * The unique identifier for the export transaction.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The item that is being exported.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", unique = true)
    private Item item;

    /**
     * The location where the item is being sent.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", unique = true)
    private Location location;

    /**
     * The income generated from the export transaction.
     */
    @Column(nullable = false)
    private Double income;

    /**
     * The quantity of items that were exported.
     */
    @Column(nullable = false)
    private Integer qty;

    /**
     * The date of the export transaction.
     */
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date transactionDate;

    // Constructors, getters, and setters
}
