package com.epam.quoteservice.integration;

import com.epam.quoteservice.cache.Cache;
import com.epam.quoteservice.exception.QuoteValidationException;
import com.epam.quoteservice.model.Quote;
import com.epam.quoteservice.service.QuoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "/application-test.properties")
public class QuoteServiceIntegrationTest {

    @Autowired
    QuoteService quoteService;


    @Autowired
    Cache cache;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testCreateQuote(){
        Quote quote1 = new Quote();
        quote1.setId(1l);
        quote1.setAsk(200.5f);
        quote1.setBid(100.1f);
        quote1.setCreationDate(LocalDateTime.now());
        quote1.setIsin("qwerty123456");

        Quote quote2 = new Quote();
        quote2.setId(2l);
        quote2.setAsk(300.5f);
        quote2.setBid(200.1f);
        quote2.setCreationDate(LocalDateTime.now());
        quote2.setIsin("asdfgh123456");

        quoteService.createQuote(quote1);
        quoteService.createQuote(quote2);

        assertEquals(2, cache.getQuoteCache().size());
        assertEquals(2, cache.getElvlCache().size());

    }

    @Test
    void testCreateQuoteWithException(){

        Quote quote = new Quote();
        quote.setId(2l);
        quote.setAsk(200.5f);
        quote.setBid(300.1f);
        quote.setCreationDate(LocalDateTime.now());
        quote.setIsin("asdfgh123456");

        assertThrows(QuoteValidationException.class,()->  quoteService.createQuote(quote));

    }


}


