package com.inventorymanagementsystem.controller;


import com.inventorymanagementsystem.dto.ItemDTO;
import com.inventorymanagementsystem.exception.BadRequestException;
import com.inventorymanagementsystem.model.Item;
import com.inventorymanagementsystem.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The ItemController class is a REST controller that provides endpoints for managing items.
 * It is responsible for handling requests to the /api/items endpoint, including creating, reading, updating, and deleting items.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {

    private final Logger log = LoggerFactory.getLogger(ItemController.class);

    private final ItemService itemService;

    /**
     * Creates a new item and returns it in the response body.
     *
     * @param itemDTO the item to create
     * @return the created item
     */
    @PostMapping
    public ResponseEntity<ItemDTO> createItem(@RequestBody ItemDTO itemDTO) {
        if (itemDTO.getId() == null)
            return ResponseEntity.status(HttpStatus.CREATED).body(itemService.createItem(itemDTO)); // http response

        log.error("the id provided is null", itemDTO );
        throw new BadRequestException(ItemController.class.getSimpleName(), "id");
    }

    /**
     * Returns a list of all items.
     *
     * @return a list of items
     */
    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        return ResponseEntity.ok().body(itemService.getAllItems());
    }

    /**
     * Returns an item with the specified ID.
     *
     * @param id the ID of the item to retrieve
     * @return the item with the specified ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getLocation(
            @PathVariable(name = "id") long id) {
        return ResponseEntity.ok().body(itemService.getItemById(id));
    }

    /**
     * Updates an existing item and returns it in the response body.
     *
     * @param itemDTO the updated item
     * @param id the ID of the item to update
     * @return the updated item
     */
    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> updateItem(
            @RequestBody ItemDTO itemDTO, @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(itemService.updateItem(itemDTO, id), HttpStatus.OK);
    }

    /**
     * Partially updates an existing item and returns it in the response body.
     *
     * @param itemDTO the partial update
     * @param id the ID of the item to update
     * @return the updated item
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ItemDTO> partiallyUpdateItem(
            @RequestBody ItemDTO itemDTO, @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(itemService.partiallyUpdateItem(itemDTO, id), HttpStatus.OK);
    }

    /**
     * Deletes an item with the specified ID.
     *
     * @param id the ID of the item to delete
     * @return an empty response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ItemDTO> deleteItemById(@PathVariable(name = "id") long id) {
        itemService.deleteItemById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
