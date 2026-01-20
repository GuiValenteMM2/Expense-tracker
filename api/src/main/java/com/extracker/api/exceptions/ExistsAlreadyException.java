package com.extracker.api.exceptions;

public class ExistsAlreadyException extends RuntimeException {
    public ExistsAlreadyException(String message) {
        super(message);
    }
}
