package com.inventorymanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author <Khalil Khawaja>
 * Main class of the Inventory Management System application.
 * Starts the Spring Boot application using the {@link SpringApplication} class.
 */
@SpringBootApplication
public class InventoryManagementSystemApplication {

    /**
     * Main method of the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(InventoryManagementSystemApplication.class, args);
    }

}
