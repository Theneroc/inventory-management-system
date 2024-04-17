package com.inventorymanagementsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The HomeController class is a Spring REST controller that provides a home page for the application.
 * The home page displays the Inventory Management System title.
 */
@RestController
public class HomeController {

    /**
     * The home method is a GET request that returns a string with the Inventory Management System title.
     * @return a string with the Inventory Management System title
     */
    @GetMapping("/")
    public String home() {
        return "Inventory Management System";
    }
}
