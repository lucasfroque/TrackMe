package com.lucasfroque.tracknme.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(Object id) {
        super("id " + id + " nao encontrado.");
    }
}
