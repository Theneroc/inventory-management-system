package com.inventorymanagementsystem.service;

import com.inventorymanagementsystem.dto.ExportDTO;
import com.inventorymanagementsystem.exception.ResourceNotFoundException;
import com.inventorymanagementsystem.model.Export;
import com.inventorymanagementsystem.model.Item;
import com.inventorymanagementsystem.model.Location;
import com.inventorymanagementsystem.repository.ExportRepository;
import com.inventorymanagementsystem.repository.ItemRepository;
import com.inventorymanagementsystem.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The ExportService class is a service class that provides business logic for exporting items from the inventory system.
 * It is responsible for creating, updating, retrieving, and deleting exports.
 */
@Service
@RequiredArgsConstructor
public class ExportService {

    private final ExportRepository exportRepository;
    private final ItemRepository itemRepository;
    private final LocationRepository locationRepository;

    /**
     * Creates a new export record in the database.
     *
     * @param exportDTO the export data transfer object
     * @return the created export data transfer object
     */
    public ExportDTO createExport(ExportDTO exportDTO) {
        Location location = locationRepository.findById(exportDTO.getLocationId())
               .orElseThrow(() -> new ResourceNotFoundException("Location", "id", exportDTO.getLocationId()));

        Item item = itemRepository.findById(exportDTO.getItemId())
               .orElseThrow(() -> new ResourceNotFoundException("Item", "id", exportDTO.getItemId()));

        Export export = exportRepository.save(mapToEntity(exportDTO, item, location));
        return mapToDTO(export);
    }

    /**
     * Retrieves all exports from the database.
     *
     * @return a list of export data transfer objects
     */
    public List<ExportDTO> getAllExports() {
        return exportRepository.findAll().stream().map(export -> mapToDTO(export))
               .collect(Collectors.toList());
    }

    /**
     * Retrieves an export by its ID.
     *
     * @param id the export ID
     * @return the export data transfer object
     */
    public ExportDTO getExportById(long id) {
        return mapToDTO(exportRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Export", "id", id)));
    }

    /**
     * Updates an existing export record in the database.
     *
     * @param exportDTO the updated export data transfer object
     * @param id the export ID
     * @return the updated export data transfer object
     */
    public ExportDTO updateExport(ExportDTO exportDTO, long id) {
        Export export = exportRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Export", "id", id));
        Location location = locationRepository.findById(exportDTO.getLocationId())
               .orElseThrow(() -> new ResourceNotFoundException("Location", "id", exportDTO.getLocationId()));

        Item item = itemRepository.findById(exportDTO.getItemId())
               .orElseThrow(() -> new ResourceNotFoundException("Item", "id", exportDTO.getItemId()));
        export.setItem(item);
        export.setLocation(location);
        export.setIncome(exportDTO.getIncome());
        export.setQty(exportDTO.getQty());
        export.setTransactionDate(exportDTO.getTransactionDate());

        return mapToDTO(export);
    }

    /**
     * Partially updates an existing export record in the database.
     *
     * @param exportDTO the updated export data transfer object
     * @param id the export ID
     * @return the updated export data transfer object
     */
    public ExportDTO partiallyUpdateExport(ExportDTO exportDTO, long id) {
        Export export = exportRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Export", "id", id));

        if (exportDTO.getItemId()!= null)
            export.setItem(itemRepository.findById(exportDTO.getItemId())
                   .orElseThrow(() -> new ResourceNotFoundException("Item", "id", exportDTO.getItemId())));

        if (exportDTO.getLocationId()!= null)
            export.setLocation(locationRepository.findById(exportDTO.getLocationId())
                   .orElseThrow(() -> new ResourceNotFoundException("Item", "id", exportDTO.getLocationId())));

        if (exportDTO.getIncome()!= null)
            export.setIncome(exportDTO.getIncome());

        if (exportDTO.getQty()!= null)
            export.setQty(exportDTO.getQty());

        if (exportDTO.getTransactionDate()!= null)
            export.setTransactionDate(exportDTO.getTransactionDate());

        Export updatedExport = exportRepository.save(export);

        return mapToDTO(updatedExport);
    }

    /**
     * Deletes an export record from the database.
     *
     * @param id the export ID
     */
    public void deleteExportById(long id) {
        Export export = exportRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Export", "id", id));
        exportRepository.delete(export);
    }

    // Maps an Export entity to an ExportDTO
    private ExportDTO mapToDTO(Export export) {
        ExportDTO exportDTO = new ExportDTO();
        exportDTO.setId(export.getId());
        exportDTO.setItemId(export.getItem().getId());
        exportDTO.setLocationId(export.getLocation().getId());
        exportDTO.setQty(export.getQty());
        exportDTO.setIncome(export.getIncome());
        exportDTO.setTransactionDate(export.getTransactionDate());
        return exportDTO;
    }

    // Maps an ExportDTO to an Export entity
    private Export mapToEntity(ExportDTO exportDTO, Item item, Location location) {
        Export export = new Export();
        export.setId(exportDTO.getId());
        export.setItem(item);
        export.setLocation(location);
        export.setQty(exportDTO.getQty());
        export.setIncome(exportDTO.getIncome());
        export.setTransactionDate(exportDTO.getTransactionDate());
        return export;
    }
}
