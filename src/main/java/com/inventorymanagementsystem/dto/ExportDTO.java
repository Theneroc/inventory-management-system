package com.inventorymanagementsystem.dto;
import lombok.Data;
import java.sql.Date;

/**
 * Data Transfer Object for exporting data from the database.
 */
@Data
public class ExportDTO {

    /**
     * Unique identifier for the record.
     */
    private Long id;

    /**
     * Foreign key to the item table.
     */
    private Long itemId;

    /**
     * Foreign key to the location table.
     */
    private Long locationId;

    /**
     * Income generated from the transaction.
     */
    private Double income;

    /**
     * Quantity of items transferred.
     */
    private Integer qty;

    /**
     * Date of the transaction.
     */
    private Date transactionDate;
}
