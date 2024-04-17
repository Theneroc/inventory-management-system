package com.inventorymanagementsystem.model;

import lombok.*;

import jakarta.persistence.*;
import java.sql.Date;

/**
 * This class represents an import transaction in the inventory management system.
 *
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Import")
public class Import {

    /**
     * The unique identifier of the import transaction.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The item imported.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", unique = true)
    private Item item;

    /**
     * The location where the item was imported from.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", unique = true)
    private Location location;

    /**
     * The cost of the import transaction.
     */
    @Column(nullable = false)
    private Double cost;

    /**
     * The quantity of the item imported.
     */
    @Column(nullable = false)
    private Integer qty;

    /**
     * The date of the import transaction.
     */
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date transactionDate;
}
