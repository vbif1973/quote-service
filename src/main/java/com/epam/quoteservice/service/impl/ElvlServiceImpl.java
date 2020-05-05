package com.epam.quoteservice.service.impl;

import com.epam.quoteservice.cache.Cache;
import com.epam.quoteservice.dto.ElvlDTO;
import com.epam.quoteservice.exception.NoDataFoundException;
import com.epam.quoteservice.repository.ElvlRepository;
import com.epam.quoteservice.service.ElvlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ElvlServiceImpl implements ElvlService {

    @Autowired
    ElvlRepository elvlRepository;

    @Autowired
    Cache cache;

    private static final Logger logger = LogManager.getLogger(ElvlServiceImpl.class);

    public List<ElvlDTO> getAllElvls() {
        ArrayList<ElvlDTO> listOfElvls = new ArrayList<>();
        cache.getElvlCache().forEach((k, v) -> listOfElvls.add(new ElvlDTO(k, v)));
        logger.info("ElvlRepository.getAllElvls called");
        return listOfElvls;
    }

    public Float getElvlByIsin(String isin) {
        Float elvl = cache.getElvlCache().get(isin);
        if (StringUtils.isEmpty(isin)) {
            throw new NoDataFoundException("Получен пустой аргумент");
        }
        logger.info(String.format("ElvlRepository.getElvlByIsin called. Returned elvl %.2f", elvl));
        return cache.getElvlCache().get(isin);
    }


}
