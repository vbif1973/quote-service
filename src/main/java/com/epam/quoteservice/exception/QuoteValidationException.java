package com.epam.quoteservice.exception;

public class QuoteValidationException extends RuntimeException {

    public QuoteValidationException(String message) {
        super(message);
    }
}
