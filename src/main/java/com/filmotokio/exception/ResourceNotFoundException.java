package com.filmotokio.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, Long id) {
        super(resourceName + " com ID " + id + " não foi encontrado.");
    }
}



