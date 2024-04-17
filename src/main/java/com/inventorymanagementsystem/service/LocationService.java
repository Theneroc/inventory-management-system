package com.inventorymanagementsystem.service;

import com.inventorymanagementsystem.dto.LocationDTO;
import com.inventorymanagementsystem.exception.ResourceNotFoundException;
import com.inventorymanagementsystem.model.Location;
import com.inventorymanagementsystem.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The LocationService class is a service class that provides business logic for managing locations.
 * It interacts with the LocationRepository to perform operations on the database.
 */
@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    /**
     * Creates a new location and saves it to the database.
     *
     * @param locationDTO the location data to be saved
     * @return the newly created location data
     */
    public LocationDTO createLocation(LocationDTO locationDTO) {
        Location location = mapToEntity(locationDTO);
        Location newLocation = locationRepository.save(location);
        LocationDTO locationResponse = mapToDTO(newLocation);
        return locationResponse;
    }

    /**
     * Returns a list of all locations in the database.
     *
     * @return a list of location data
     */
    public List<LocationDTO> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream().map(this::mapToDTO)
               .collect(Collectors.toList());
    }

    /**
     * Returns the location with the specified ID.
     *
     * @param id the ID of the location to be returned
     * @return the location data
     */
    public LocationDTO getLocationById(long id) {
        Location location = locationRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Location", "id", id));
        return mapToDTO(location);
    }

    /**
     * Updates an existing location in the database.
     *
     * @param locationDTO the updated location data
     * @param id the ID of the location to be updated
     * @return the updated location data
     */
    public LocationDTO updateLocation(LocationDTO locationDTO, long id) {
        Location location = locationRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Location", "id", id));

        location.setAddress(locationDTO.getAddress());
        location.setEmailAddress(locationDTO.getEmailAddress());
        location.setPhoneNum(locationDTO.getPhoneNum());

        Location updatedLocation = locationRepository.save(location);
        return mapToDTO(updatedLocation);
    }

    /**
     * Partially updates an existing location in the database.
     *
     * @param locationDTO the partial location data
     * @param id the ID of the location to be updated
     * @return the updated location data
     */
    public LocationDTO partiallyUpdateLocation(LocationDTO locationDTO, long id) {
        Location location = locationRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Location", "id", id));

        if (locationDTO.getAddress()!= null) {
            location.setAddress(locationDTO.getAddress());
        }
        if (locationDTO.getEmailAddress()!= null) {
            location.setEmailAddress(locationDTO.getEmailAddress());
        }
        if (locationDTO.getPhoneNum()!= null) {
            location.setPhoneNum(locationDTO.getPhoneNum());
        }

        Location updatedLocation = locationRepository.save(location);

        return mapToDTO(updatedLocation);
    }

    /**
     * Deletes the location with the specified ID from the database.
     *
     * @param id the ID of the location to be deleted
     */
    public void deleteLocationById(long id) {
        Location location = locationRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Location", "id", id));
        locationRepository.delete(location);
    }

    // map Entity to DTO
    private LocationDTO mapToDTO(Location location) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(location.getId());
        locationDTO.setAddress(location.getAddress());
        locationDTO.setEmailAddress(location.getEmailAddress());
        locationDTO.setPhoneNum(location.getPhoneNum());
        return locationDTO;
    }

    // map DTO to Entity
    private Location mapToEntity(LocationDTO locationDTO) {
        Location location = new Location();
        location.setId(locationDTO.getId());
        location.setAddress(locationDTO.getAddress());
        location.setEmailAddress(locationDTO.getEmailAddress());
        location.setPhoneNum(locationDTO.getPhoneNum());
        return location;
    }
}
