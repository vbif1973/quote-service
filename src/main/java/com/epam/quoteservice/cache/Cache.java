package com.epam.quoteservice.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Cache {

    @Autowired
    private Set quoteCache;

    @Autowired
    private ConcurrentHashMap<String, Float> elvlCache;

    public Set getQuoteCache() {
        return quoteCache;
    }

    public ConcurrentHashMap<String, Float> getElvlCache() {
        return elvlCache;
    }

}
