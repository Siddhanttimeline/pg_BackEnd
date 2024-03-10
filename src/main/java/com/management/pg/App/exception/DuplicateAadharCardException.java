package com.management.pg.App.exception;

public class DuplicateAadharCardException extends RuntimeException {
    public DuplicateAadharCardException(String message) {
        super(message);
    }
}