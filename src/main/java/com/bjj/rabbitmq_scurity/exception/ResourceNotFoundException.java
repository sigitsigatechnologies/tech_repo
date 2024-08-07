package com.bjj.rabbitmq_scurity.exception;

import lombok.Data;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private long fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format(resourceName, fieldName, fieldValue)); // participant not found with id : 1
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String message, Throwable cause, String resourceName, String fieldName) {
        super(message, cause);
        this.resourceName = resourceName;
        this.fieldName = fieldName;
    }

    public ResourceNotFoundException(String message, String resourceName) {
        super(message);
        this.resourceName = resourceName;
        this.fieldName = fieldName;
    }
}
