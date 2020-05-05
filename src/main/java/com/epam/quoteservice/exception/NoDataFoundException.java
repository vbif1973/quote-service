package com.epam.quoteservice.exception;

public class NoDataFoundException extends RuntimeException {

    public NoDataFoundException(String message) {
        super(message);
    }
}
