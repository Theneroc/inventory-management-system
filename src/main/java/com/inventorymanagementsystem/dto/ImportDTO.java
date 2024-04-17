package com.inventorymanagementsystem.dto;
import lombok.Data;
import java.sql.Date;

/**
 * Data Transfer Object for Import entity.
 */
@Data
public class ImportDTO {

    private Long id;

    private Long itemId;

    private Long locationId;

    private Double cost;

    private Integer qty;

    private Date transactionDate;

    /**
     * Default constructor.
     */
    public ImportDTO() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id              ID
     * @param itemId          item ID
     * @param locationId      location ID
     * @param cost            cost
     * @param qty             quantity
     * @param transactionDate transaction date
     */
    public ImportDTO(Long id, Long itemId, Long locationId, Double cost, Integer qty, Date transactionDate) {
        this.id = id;
        this.itemId = itemId;
        this.locationId = locationId;
        this.cost = cost;
        this.qty = qty;
        this.transactionDate = transactionDate;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return string representation of the object
     */
    @Override
    public String toString() {
        return "ImportDTO{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", locationId=" + locationId +
                ", cost=" + cost +
                ", qty=" + qty +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
