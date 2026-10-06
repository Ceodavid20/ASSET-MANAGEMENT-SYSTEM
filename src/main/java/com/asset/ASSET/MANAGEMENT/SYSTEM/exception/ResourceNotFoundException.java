package com.asset.ASSET.MANAGEMENT.SYSTEM.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}