package com.inventorymanagementsystem.controller;


import com.inventorymanagementsystem.dto.ImportDTO;
import com.inventorymanagementsystem.exception.BadRequestException;
import com.inventorymanagementsystem.service.ImportService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The ImportController class is a REST controller that provides endpoints for managing Import entities.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/imports")
public class ImportController {

    private final Logger log = LoggerFactory.getLogger(ImportController.class);

    private final ImportService importService;

    /**
     * Creates a new Import entity based on the information provided in the request body.
     *
     * @param importDTO the ImportDTO object containing the information for the new Import
     * @return a ResponseEntity object containing the newly created ImportDTO object and a status code
     */
    @PostMapping
    public ResponseEntity<ImportDTO> createImport(@RequestBody ImportDTO importDTO) {
        if (importDTO.getId() == null)
            return ResponseEntity.status(HttpStatus.CREATED).body(importService.createImport(importDTO)); // http response

        log.error("the id provided is null", importDTO );
        throw new BadRequestException(ImportController.class.getSimpleName(), "id");
    }

    /**
     * Returns a list of all Import entities.
     *
     * @return a ResponseEntity object containing a list of ImportDTO objects and a status code
     */
    @GetMapping
    public ResponseEntity<List<ImportDTO>> getAllImports() {
        return ResponseEntity.ok().body(importService.getAllImports());
    }

    /**
     * Returns the Import entity with the specified ID.
     *
     * @param id the ID of the Import entity to be returned
     * @return a ResponseEntity object containing the ImportDTO object and a status code
     */
    @GetMapping("/{id}")
    public ResponseEntity<ImportDTO> getImport(
            @PathVariable(name = "id") long id) {
        return ResponseEntity.ok().body(importService.getImportById(id));
    }

    /**
     * Updates the information for the Import entity with the specified ID.
     *
     * @param importDTO the ImportDTO object containing the updated information for the Import
     * @param id the ID of the Import entity to be updated
     * @return a ResponseEntity object containing the updated ImportDTO object and a status code
     */
    @PutMapping("/{id}")
    public ResponseEntity<ImportDTO> updateImport(
            @RequestBody ImportDTO importDTO, @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(importService.updateImport(importDTO, id), HttpStatus.OK);
    }

    /**
     * Partially updates the information for the Import entity with the specified ID.
     *
     * @param importDTO the ImportDTO object containing the partial updated information for the Import
     * @param id the ID of the Import entity to be partially updated
     * @return a ResponseEntity object containing the partially updated ImportDTO object and a status code
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ImportDTO> partiallyUpdateItem(
            @RequestBody ImportDTO importDTO, @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(importService.partiallyUpdateImport(importDTO, id), HttpStatus.OK);
    }

    /**
     * Deletes the Import entity with the specified ID.
     *
     * @param id the ID of the Import entity to be deleted
     * @return a ResponseEntity object containing a status code
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ImportDTO> deleteItemById(@PathVariable(name = "id") long id) {
        importService.deleteImportById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

