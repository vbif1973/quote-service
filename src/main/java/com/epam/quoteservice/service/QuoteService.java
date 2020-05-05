package com.epam.quoteservice.service;

import com.epam.quoteservice.dto.QuoteDTO;
import com.epam.quoteservice.model.Quote;

public interface QuoteService {

    void createQuoteDirect(Quote quote);

    void createQuote(Quote quote);
}
