package com.challenge.exceptions;

public class ResourceNotFundException extends RuntimeException {
    public ResourceNotFundException(String resourceName) {
        super(("Resource " + resourceName + " not found."));
    }
}
