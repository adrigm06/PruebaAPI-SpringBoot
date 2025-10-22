package com.example.pruebaapi.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String menssage) {
        super(menssage);
    }
}
