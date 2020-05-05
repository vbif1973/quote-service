package com.epam.quoteservice.controller;

import com.epam.quoteservice.dto.ElvlDTO;
import com.epam.quoteservice.service.ElvlService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/elvls")
public class ElvlController {

    @Autowired
    ElvlService elvlService;

    private static final Logger logger = LogManager.getLogger(ElvlController.class);

    @GetMapping("/allelvls")
    public List<ElvlDTO> getAllElvls() {
        logger.info("Called ElvlController.getAllElvls");
        return elvlService.getAllElvls();
    }

    @GetMapping("/elvlByIsin/{isin}")
    public Float getElvlByIsin(@PathVariable String isin) {
        logger.info("Called ElvlController.getElvlByIsin");
        return elvlService.getElvlByIsin(isin);
    }

}
