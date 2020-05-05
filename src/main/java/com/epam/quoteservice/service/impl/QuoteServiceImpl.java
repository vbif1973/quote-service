package com.epam.quoteservice.service.impl;

import com.epam.quoteservice.cache.Cache;
import com.epam.quoteservice.dto.QuoteDTO;
import com.epam.quoteservice.exception.QuoteValidationException;
import com.epam.quoteservice.model.Quote;
import com.epam.quoteservice.repository.QuoteRepository;
import com.epam.quoteservice.service.QuoteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Set;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    QuoteRepository quoteRepository;

    @Autowired
    Cache cache;

    @Value("${quote.maxsize}")
    int quoteMaxSize;

    private static final Logger logger = LogManager.getLogger(QuoteServiceImpl.class);

    public void createQuoteDirect(Quote quote) {
        quoteRepository.save(quote);
    }

    public void createQuote(Quote quote) {
        validateQuote(quote);
//        Кладем котировку в кэш котировок
        cache.getQuoteCache().add(quote);
        logger.info(String.format("QuoteServiceImpl.createQuote called. QuoteCache size is %d", cache.getQuoteCache().size()));
        Float calculatedElvl = calculateElvl(quote);
//        Кладем elvl в кэш уровней
        cache.getElvlCache().put(quote.getIsin(), calculatedElvl);
        logger.info(String.format("Added elvl = %.2f to cache for isin %s", calculatedElvl, quote.getIsin()));
        saveQuotesAndPurgeQuoteCache();

    }

    private void validateQuote(Quote quote) {
        if(quote.getIsin().length() != 12) {
            throw new QuoteValidationException(String.format("Isin %s must be 12 characters", quote.getIsin()));
        }
        if ((quote.getAsk() != null)
            &&(quote.getBid() != null)
            &&(quote.getAsk().compareTo(quote.getBid())) <= 0) {
            throw new QuoteValidationException(String.format("Ask %s must be greater than bid %s", quote.getAsk(), quote.getBid()));
        }
    }

    @Transactional
    private void saveQuotesAndPurgeQuoteCache() {
        if (cache.getQuoteCache().size() >= quoteMaxSize) {
                quoteRepository.saveAll(cache.getQuoteCache());
                cache.getQuoteCache().clear();
                logger.info(String.format("QuoteCache saved to DB and cleared. QuoteCache size is %d", cache.getQuoteCache().size()));
            }
        }

    private Float calculateElvl(Quote quote) {
        Float currentElvl = cache.getElvlCache().get(quote.getIsin());
        Float calculatedElvl = null;
        if (currentElvl == null) {
            calculatedElvl = quote.getBid();
        } else if (quote.getBid() == null) {
            calculatedElvl = quote.getAsk();
        } else if (quote.getBid().compareTo(currentElvl) == 1) {
            calculatedElvl = quote.getBid();
        } else if (quote.getAsk().compareTo(currentElvl) == -1) {
            calculatedElvl = quote.getAsk();
        } else {
            return currentElvl;
        }

        return calculatedElvl;
    }

}
