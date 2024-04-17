package com.inventorymanagementsystem.dto;
import lombok.Data;
import java.sql.Date;

/**
 * Data Transfer Object for an Item.
 */
@Data
public class ItemDTO {

    /**
     * The unique identifier for the item.
     */
    private Long id;

    /**
     * The location identifier for the item.
     */
    private Long locationId;

    /**
     * The name of the item.
     */
    private String name;

    /**
     * The cost of the item.
     */
    private Double cost;

    /**
     * The markup percentage of the item.
     */
    private Float markup;

    /**
     * The quantity of the item.
     */
    private Integer qty;

    /**
     * The expiration date of the item.
     */
    private Date expiration;
}
