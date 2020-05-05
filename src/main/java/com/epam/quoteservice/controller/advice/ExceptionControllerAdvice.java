package com.epam.quoteservice.controller.advice;

import com.epam.quoteservice.exception.NoDataFoundException;
import com.epam.quoteservice.exception.QuoteValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ExceptionControllerAdvice {

    private static final Logger logger = LogManager.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler(QuoteValidationException.class)
    public ResponseEntity<Object> handleQuoteValidationException(QuoteValidationException e) {
        logger.info(String.format("ExceptionControllerAdvice handled QuoteValidationException %s", e.getMessage()));
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Object> handleNoDataFoundException(NoDataFoundException e) {
        logger.info(String.format("ExceptionControllerAdvice handled NoDataFoundException", e.getMessage()));
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

}
