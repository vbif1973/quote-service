package com.epam.quoteservice.service.impl;

import com.epam.quoteservice.cache.Cache;
import com.epam.quoteservice.exception.QuoteValidationException;
import com.epam.quoteservice.model.Quote;
import com.epam.quoteservice.repository.QuoteRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QuoteServiceImplTest {

    @InjectMocks
    QuoteServiceImpl quoteService;

    @Mock
    QuoteRepository quoteRepository;

    @Mock
    Cache cache;

    @Test
    void testCalculateElvl() {

        //GIVEN
        ConcurrentHashMap<String, Float> quoteCache = new ConcurrentHashMap<>();
        quoteCache.put("qwerty123456", 12.12f);
        Mockito.when(cache.getElvlCache()).thenReturn(quoteCache);

        Quote quote = new Quote();
        quote.setId(1l);
        quote.setAsk(200.5f);
        quote.setBid(100.1f);
        quote.setCreationDate(LocalDateTime.now());
        quote.setIsin("qwerty123456");


        //WHEN
        Float result = quoteService.calculateElvl(quote);
        //THEN
        assertTrue(result.equals(100.1f));

        //Котировка с пустым bid
        quote.setAsk(200.5f);
        quote.setBid(null);
        //WHEN
        result = quoteService.calculateElvl(quote);
        //THEN
        assertTrue(result.equals(200.5f));

        //Котировка с bid > elvl
        quoteCache.put("qwerty123456", 200.5f);
        quote.setBid(250.5f);
        quote.setAsk(300.5f);
        //WHEN
        result = quoteService.calculateElvl(quote);
        //THEN
        assertTrue(result.equals(250.5f));

        //Котировка с ask < elvl
        quoteCache.put("qwerty123456", 250.5f);
        quote.setBid(220.5f);
        quote.setAsk(240.5f);
        //WHEN
        result = quoteService.calculateElvl(quote);
        //THEN
        assertTrue(result.equals(240.5f));
    }


    @Test
    void testValidateQuoteExceededLength() {

        Quote quote = new Quote();
        quote.setId(1l);
        quote.setAsk(200.5f);
        quote.setBid(100.1f);
        quote.setCreationDate(LocalDateTime.now());
        quote.setIsin("qwerty123456789");
        assertThrows(QuoteValidationException.class,()->  quoteService.validateQuote(quote));
    }


    @Test
    void testAskIsNotGreaterThanBid() {

        Quote quote = new Quote();
        quote.setId(1l);
        quote.setAsk(20.5f);
        quote.setBid(100.1f);
        quote.setCreationDate(LocalDateTime.now());
        quote.setIsin("qwerty123456");
        assertThrows(QuoteValidationException.class,()->  quoteService.validateQuote(quote));
    }

}
