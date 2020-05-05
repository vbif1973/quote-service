package com.epam.quoteservice.controller;

import com.epam.quoteservice.dto.QuoteDTO;
import com.epam.quoteservice.model.Quote;
import com.epam.quoteservice.service.QuoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QuoteControllerTest {

    @InjectMocks
    QuoteController quoteController;

    @Mock
    QuoteService quoteService;

    @Mock
    ModelMapper modelMapper;

    @Test
    void testCreateQuote() {
        //GIVEN
        ResponseEntity<String> responseEntity = new ResponseEntity<>("Котировка создана", HttpStatus.CREATED);
        QuoteDTO dto = new QuoteDTO("qwerty123456", 12.12f, 15.15f);
        Quote quote =  modelMapper.map(dto, Quote.class);
        Mockito.when(quoteService.createQuote(quote)).thenReturn("Quote saved successfully");
        Mockito.when(modelMapper.map(dto, Quote.class)).thenReturn(quote);
        //WHEN
        ResponseEntity<String> result =  quoteController.createQuote(dto);
        //THEN
        assertTrue(result.getBody().toString().contains("Котировка создана"));
        assertTrue(result.getStatusCode().equals(HttpStatus.CREATED));
    }

}