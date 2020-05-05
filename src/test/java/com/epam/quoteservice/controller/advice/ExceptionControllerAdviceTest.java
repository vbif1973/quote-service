package com.epam.quoteservice.controller.advice;

import com.epam.quoteservice.exception.NoDataFoundException;
import com.epam.quoteservice.exception.QuoteValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class ExceptionControllerAdviceTest {

    ExceptionControllerAdvice exceptionControllerAdvice = new ExceptionControllerAdvice();

    @Test
    void handleQuoteValidationException() {
        // GIVEN
        QuoteValidationException exception = new QuoteValidationException("неверный формат");
        // WHEN
        ResponseEntity responseEntity = exceptionControllerAdvice.handleQuoteValidationException(exception);
        // THEN
        Map<String, Object> body = (Map<String, Object>) responseEntity.getBody();
        assertTrue(body.get("message").toString().contains("неверный формат"));
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void handleNoDataFoundException() {
        // GIVEN
        NoDataFoundException exception = new NoDataFoundException("данные не найдены");
        // WHEN
        ResponseEntity responseEntity = exceptionControllerAdvice.handleNoDataFoundException(exception);
        // THEN
        Map<String, Object> body = (Map<String, Object>) responseEntity.getBody();
        assertTrue(body.get("message").toString().contains("данные не найдены"));
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}