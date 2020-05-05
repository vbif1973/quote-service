package com.epam.quoteservice.controller;

import com.epam.quoteservice.dto.QuoteDTO;
import com.epam.quoteservice.model.Quote;
import com.epam.quoteservice.service.QuoteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    @Autowired
    QuoteService quoteService;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger logger = LogManager.getLogger(QuoteController.class);

    @PostMapping(value="/createquotedirect", consumes={"application/json"})
    public ResponseEntity<String> createQuoteDirect(@RequestBody QuoteDTO quoteDTO) {
        logger.info("QuoteController.createQuoteDirect called");
        Quote quote = modelMapper.map(quoteDTO, Quote.class);
        quoteService.createQuoteDirect(quote);
        return new ResponseEntity<>("Котировка создана", HttpStatus.CREATED);
    }

    @PostMapping(value="/createquote", consumes={"application/json"})
    public ResponseEntity<String> createQuote(@RequestBody QuoteDTO quoteDTO) {
        logger.info("QuoteController.createQuote called");
        Quote quote = modelMapper.map(quoteDTO, Quote.class);
        quoteService.createQuote(quote);
        return new ResponseEntity<>("Котировка создана", HttpStatus.CREATED);
    }

}
