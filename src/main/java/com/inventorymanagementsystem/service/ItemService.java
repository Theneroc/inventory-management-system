package com.inventorymanagementsystem.service;

import com.inventorymanagementsystem.dto.ItemDTO;
import com.inventorymanagementsystem.exception.ResourceNotFoundException;
import com.inventorymanagementsystem.model.Item;
import com.inventorymanagementsystem.model.Location;
import com.inventorymanagementsystem.repository.ItemRepository;
import com.inventorymanagementsystem.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The ItemService class is a service class that provides business logic for managing items. It uses the
 * ItemRepository to interact with the database and the LocationRepository to retrieve location information.
 */
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final LocationRepository locationRepository;

    /**
     * Creates a new item and saves it to the database.
     *
     * @param itemDTO the itemDTO object containing the item information
     * @return the newly created itemDTO
     */
    public ItemDTO createItem(ItemDTO itemDTO) {
        Location location = locationRepository.findById(itemDTO.getLocationId())
               .orElseThrow(() -> new ResourceNotFoundException("Location", "id", itemDTO.getLocationId()));
        Item item = mapToEntity(itemDTO, location);
        Item newItem = itemRepository.save(item); //new item,  bc the db can return added data like primary key or
        // default vales
        return mapToDTO(newItem);
    }

    /**
     * Returns a list of all items in the database.
     *
     * @return a list of all items
     */
    public List<ItemDTO> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream().map(item -> mapToDTO(item))
               .collect(Collectors.toList());
    }

    /**
     * Returns an item based on its ID.
     *
     * @param id the ID of the item
     * @return the itemDTO for the specified ID
     */
    public ItemDTO getItemById(long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));
        return mapToDTO(item);
    }

    /**
     * Updates an existing item in the database.
     *
     * @param itemDTO the updated itemDTO
     * @param id      the ID of the item to update
     * @return the updated itemDTO
     */
    public ItemDTO updateItem(ItemDTO itemDTO, long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));
        Location location = locationRepository.findById(itemDTO.getLocationId())
               .orElseThrow();

        item.setName(itemDTO.getName());
        item.setCost(itemDTO.getCost());
        item.setMarkup(itemDTO.getMarkup());
        item.setQty(itemDTO.getQty());
        item.setExpiration(itemDTO.getExpiration());
        item.setLocation(location);

        Item updatedItem = itemRepository.save(item);
        return mapToDTO(updatedItem);
    }

    /**
     * Partially updates an existing item in the database.
     *
     * @param itemDTO the updated itemDTO
     * @param id      the ID of the item to update
     * @return the updated itemDTO
     */
    public ItemDTO partiallyUpdateItem(ItemDTO itemDTO, long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));

        if (itemDTO.getLocationId()!= null)
            item.setLocation(locationRepository.findById(itemDTO.getLocationId())
                   .orElseThrow(() -> new ResourceNotFoundException("Location", "id", id)));

        if (itemDTO.getExpiration()!= null)
            item.setExpiration(itemDTO.getExpiration());

        if (itemDTO.getCost()!= null)
            item.setCost(itemDTO.getCost());

        if (itemDTO.getMarkup()!= null)
            item.setMarkup(itemDTO.getMarkup());

        if (itemDTO.getName()!= null)
            item.setName(itemDTO.getName());

        if (itemDTO.getQty()!= null)
            item.setQty(itemDTO.getQty());

        Item updatedItem = itemRepository.save(item);

        return mapToDTO(updatedItem);
    }

    /**
     * Deletes an item from the database based on its ID.
     *
     * @param id the ID of the item to delete
     */
    public void deleteItemById(long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));
        itemRepository.delete(item);
    }

    //map Entity to DTO
    private ItemDTO mapToDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setLocationId(item.getLocation().getId());
        itemDTO.setName(item.getName());
        itemDTO.setCost(item.getCost());
        itemDTO.setMarkup(item.getMarkup());
        itemDTO.setQty(item.getQty());
        itemDTO.setExpiration(item.getExpiration());
        return itemDTO;
    }

    //map DTO to Entity
    private Item mapToEntity(ItemDTO itemDTO, Location location) {
        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setCost(itemDTO.getCost());
        item.setMarkup(itemDTO.getMarkup());
        item.setQty(itemDTO.getQty());
        item.setExpiration(itemDTO.getExpiration());
        item.setLocation(location);
        return item;
    }
}
