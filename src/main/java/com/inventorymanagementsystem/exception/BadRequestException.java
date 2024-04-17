package com.inventorymanagementsystem.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * This exception is used to indicate that the request is malformed or contains invalid data.
 * It is used to return an error response to the client.
 */
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String resourceName;
    private final String fieldName;

    /**
     * Creates a new BadRequestException with the given resource name and field name.
     *
     * @param resourceName the name of the resource that is invalid
     * @param fieldName    the name of the field that is invalid
     */
    public BadRequestException(String resourceName, String fieldName) {
        super(String.format("%s has wrong %s value'", resourceName, fieldName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
    }

    /**
     * Returns a map containing the parameters for the error response.
     *
     * @param resourceName the name of the resource that is invalid
     * @param fieldName    the name of the field that is invalid
     * @return a map containing the parameters for the error response
     */
    public static Map<String, Object> getParameters(String resourceName, String fieldName) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", "error." + fieldName);
        parameters.put("params", resourceName);
        return parameters;
    }
}
