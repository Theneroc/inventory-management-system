package com.inventorymanagementsystem.service;

import com.inventorymanagementsystem.dto.ImportDTO;
import com.inventorymanagementsystem.exception.ResourceNotFoundException;
import com.inventorymanagementsystem.model.Import;
import com.inventorymanagementsystem.model.Item;
import com.inventorymanagementsystem.model.Location;
import com.inventorymanagementsystem.repository.ImportRepository;
import com.inventorymanagementsystem.repository.ItemRepository;
import com.inventorymanagementsystem.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing imports
 */
@Service
@RequiredArgsConstructor
public class ImportService {

    private final ImportRepository importRepository;
    private final ItemRepository itemRepository;
    private final LocationRepository locationRepository;

    /**
     * Creates a new import
     *
     * @param importDTO the import data
     * @return the created import
     */
    public ImportDTO createImport(ImportDTO importDTO) {
        Location location = locationRepository.findById(importDTO.getLocationId())
               .orElseThrow(() -> new ResourceNotFoundException("Location", "id", importDTO.getLocationId()));

        Item item = itemRepository.findById(importDTO.getItemId())
               .orElseThrow(() -> new ResourceNotFoundException("Item", "id", importDTO.getItemId()));

        Import imp = importRepository.save(mapToEntity(importDTO, item, location));
        return mapToDTO(imp);
    }

    /**
     * Gets all imports
     *
     * @return a list of imports
     */
    public List<ImportDTO> getAllImports() {
        return importRepository.findAll().stream().map(imp -> mapToDTO(imp))
               .collect(Collectors.toList());
    }

    /**
     * Gets an import by its ID
     *
     * @param id the ID of the import
     * @return the import with the given ID
     */
    public ImportDTO getImportById(long id) {
        return mapToDTO(importRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Import", "id", id)));
    }

    /**
     * Updates an import
     *
     * @param importDTO the updated import data
     * @param id        the ID of the import to update
     * @return the updated import
     */
    public ImportDTO updateImport(ImportDTO importDTO, long id) {
        Import imp = importRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Import", "id", id));
        Location location = locationRepository.findById(importDTO.getLocationId())
               .orElseThrow(() -> new ResourceNotFoundException("Location", "id", importDTO.getLocationId()));

        Item item = itemRepository.findById(importDTO.getItemId())
               .orElseThrow(() -> new ResourceNotFoundException("Item", "id", importDTO.getItemId()));
        imp.setItem(item);
        imp.setLocation(location);
        imp.setCost(importDTO.getCost());
        imp.setQty(importDTO.getQty());
        imp.setTransactionDate(importDTO.getTransactionDate());

        return mapToDTO(imp);
    }

    /**
     * Partially updates an import
     *
     * @param importDTO the updated import data
     * @param id        the ID of the import to update
     * @return the updated import
     */
    public ImportDTO partiallyUpdateImport(ImportDTO importDTO, long id) {
        Import imp = importRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Import", "id", id));

        if (importDTO.getItemId()!= null)
            imp.setItem(itemRepository.findById(importDTO.getItemId())
                   .orElseThrow(() -> new ResourceNotFoundException("Item", "id", importDTO.getItemId())));

        if (importDTO.getLocationId()!= null)
            imp.setLocation(locationRepository.findById(importDTO.getLocationId())
                   .orElseThrow(() -> new ResourceNotFoundException("Item", "id", importDTO.getLocationId())));

        if (importDTO.getCost()!= null)
            imp.setCost(importDTO.getCost());

        if (importDTO.getQty()!= null)
            imp.setQty(importDTO.getQty());

        if (importDTO.getTransactionDate()!= null)
            imp.setTransactionDate(importDTO.getTransactionDate());

        Import updatedImport = importRepository.save(imp);

        return mapToDTO(updatedImport);
    }

    /**
     * Deletes an import by its ID
     *
     * @param id the ID of the import to delete
     */
    public void deleteImportById(long id) {
        Import imp = importRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Import", "id", id));
        importRepository.delete(imp);
    }

    // Maps an Import entity to an ImportDTO
    private ImportDTO mapToDTO(Import imp) {
        ImportDTO impDTO = new ImportDTO();
        impDTO.setId(imp.getId());
        impDTO.setItemId(imp.getItem().getId());
        impDTO.setLocationId(imp.getLocation().getId());
        impDTO.setQty(imp.getQty());
        impDTO.setCost(imp.getCost());
        impDTO.setTransactionDate(imp.getTransactionDate());
        return impDTO;
    }

    // Maps an ImportDTO to an Import entity
    private Import mapToEntity(ImportDTO impDTO, Item item, Location location) {
        Import imp = new Import();
        imp.setId(impDTO.getId());
        imp.setItem(item);
        imp.setLocation(location);
        imp.setQty(impDTO.getQty());
        imp.setCost(impDTO.getCost());
        imp.setTransactionDate(impDTO.getTransactionDate());
        return imp;
    }
}
