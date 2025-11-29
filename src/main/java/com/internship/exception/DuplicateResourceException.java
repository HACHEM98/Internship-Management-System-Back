package com.internship.exception;

/**
 * Exception thrown when attempting to create a duplicate resource
 * 
 * @author Internship Management System
 */
public class DuplicateResourceException extends RuntimeException {
    
    public DuplicateResourceException(String message) {
        super(message);
    }
}

