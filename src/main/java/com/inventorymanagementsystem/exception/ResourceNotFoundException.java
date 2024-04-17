package com.inventorymanagementsystem.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * A custom exception class to handle resource not found scenarios.
 * This exception is used to return a specific HTTP status code of 404 Not Found.
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String resourceName;
    private String fieldName;
    private long fieldValue;

    /**
     * Constructs a new {@code ResourceNotFoundException} with the given resource name, field name, and field value.
     *
     * @param resourceName the name of the resource
     * @param fieldName    the name of the field
     * @param fieldValue   the value of the field
     */
    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    /**
     * Returns the name of the resource.
     *
     * @return the name of the resource
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * Returns the name of the field.
     *
     * @return the name of the field
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Returns the value of the field.
     *
     * @return the value of the field
     */
    public long getFieldValue() {
        return fieldValue;
    }

}
