package com.inventorymanagementsystem.controller;


import com.inventorymanagementsystem.dto.LocationDTO;
import com.inventorymanagementsystem.exception.BadRequestException;
import com.inventorymanagementsystem.model.Location;
import com.inventorymanagementsystem.service.LocationService;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The LocationController class is a REST controller that provides endpoints for managing locations.
 * It is responsible for handling requests to the /api/locations endpoint and its sub-routes.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/locations")
public class LocationController {
    Logger log = LoggerFactory.getLogger(LocationController.class);
    private final LocationService locationService;

    /**
     * Creates a new location and returns it in the response body.
     *
     * @param locationDTO the location data to create
     * @return the created location
     */
    @PostMapping
    public ResponseEntity<LocationDTO> createLocation(@Validated @RequestBody LocationDTO locationDTO) {
        if (locationDTO.getId() == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(locationService.createLocation(locationDTO));
        }

        log.error("the id provided is null", locationDTO);
        throw new BadRequestException(LocationController.class.getSimpleName(), "id");
    }

    /**
     * Returns a list of all locations.
     *
     * @return a list of locations
     */
    @GetMapping
    public ResponseEntity<List<LocationDTO>> getAllLocations() {
        return ResponseEntity.ok().body(locationService.getAllLocations());
    }

    /**
     * Returns the location with the given ID.
     *
     * @param id the ID of the location to retrieve
     * @return the location with the given ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> getLocation(@PathVariable long id) {
        return ResponseEntity.ok().body(locationService.getLocationById(id));
    }

    /**
     * Updates the location with the given ID and returns it in the response body.
     *
     * @param locationDTO the location data to update
     * @param id          the ID of the location to update
     * @return the updated location
     */
    @PutMapping("/{id}")
    public ResponseEntity<LocationDTO> updateLocation(@RequestBody LocationDTO locationDTO, @PathVariable long id) {
        return new ResponseEntity<>(locationService.updateLocation(locationDTO, id), HttpStatus.OK);
    }

    /**
     * Partially updates the location with the given ID and returns it in the response body.
     *
     * @param locationDTO the location data to partially update
     * @param id          the ID of the location to partially update
     * @return the partially updated location
     */
    @PatchMapping("/{id}")
    public ResponseEntity<LocationDTO> partiallyUpdateLocation(@RequestBody LocationDTO locationDTO, @PathVariable long id) {
        return new ResponseEntity<>(locationService.partiallyUpdateLocation(locationDTO, id), HttpStatus.OK);
    }

    /**
     * Deletes the location with the given ID.
     *
     * @param id the ID of the location to delete
     * @return an empty response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<LocationDTO> deleteLocation(@PathVariable long id) {
        locationService.deleteLocationById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
