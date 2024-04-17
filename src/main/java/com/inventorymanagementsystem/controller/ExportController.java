package com.inventorymanagementsystem.controller;


import com.inventorymanagementsystem.dto.ExportDTO;
import com.inventorymanagementsystem.exception.BadRequestException;
import com.inventorymanagementsystem.service.ExportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The ExportController class is a REST controller that provides endpoints for managing exports.
 * It is responsible for handling requests to create, retrieve, update, and delete exports.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exports")
public class ExportController {

    private final ExportService exportService;
    Logger log = LoggerFactory.getLogger(ExportController.class);

    /**
     * Creates a new export.
     *
     * @param exportDTO the export data
     * @return the created export
     */
    @PostMapping
    public ResponseEntity<ExportDTO> createExport(@RequestBody ExportDTO exportDTO) {
        if (exportDTO.getId() == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(exportService.createExport(exportDTO));
        }

        log.error("the id provided is null", exportDTO);
        throw new BadRequestException(ExportController.class.getSimpleName(), "id");
    }

    /**
     * Retrieves all exports.
     *
     * @return a list of exports
     */
    @GetMapping
    public ResponseEntity<List<ExportDTO>> getAllExports() {
        return ResponseEntity.ok().body(exportService.getAllExports());
    }

    /**
     * Retrieves an export by ID.
     *
     * @param id the ID of the export
     * @return the export with the given ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExportDTO> getExport(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok().body(exportService.getExportById(id));
    }

    /**
     * Updates an export.
     *
     * @param exportDTO the updated export data
     * @param id        the ID of the export
     * @return the updated export
     */
    @PutMapping("/{id}")
    public ResponseEntity<ExportDTO> updateExport(@RequestBody ExportDTO exportDTO, @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(exportService.updateExport(exportDTO, id), HttpStatus.OK);
    }

    /**
     * Partially updates an export.
     *
     * @param exportDTO the partial export data
     * @param id        the ID of the export
     * @return the updated export
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ExportDTO> partiallyUpdateExport(@RequestBody ExportDTO exportDTO, @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(exportService.partiallyUpdateExport(exportDTO, id), HttpStatus.OK);
    }

    /**
     * Deletes an export.
     *
     * @param id the ID of the export
     * @return an empty response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ExportDTO> deleteExport(@PathVariable(name = "id") long id) {
        exportService.deleteExportById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
