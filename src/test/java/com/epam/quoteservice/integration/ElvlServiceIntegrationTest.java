package com.epam.quoteservice.integration;

import com.epam.quoteservice.cache.Cache;
import com.epam.quoteservice.dto.ElvlDTO;
import com.epam.quoteservice.dto.QuoteDTO;
import com.epam.quoteservice.model.Quote;
import com.epam.quoteservice.service.ElvlService;
import com.epam.quoteservice.service.QuoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "/application-test.properties")
public class ElvlServiceIntegrationTest {

    @Autowired
    ElvlService elvlService;

    @Autowired
    QuoteService quoteService;

    @Test
    void testGetAllElvls() {
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

        List<ElvlDTO> expectedList = new ArrayList<>();

        ElvlDTO elvlDTO1 = new ElvlDTO();
        elvlDTO1.setIsin("qwerty123456");
        elvlDTO1.setElvl(200.5f);

        ElvlDTO elvlDTO2 = new ElvlDTO();
        elvlDTO2.setIsin("asdfgh123456");
        elvlDTO2.setElvl(200.1f);

        expectedList.add(elvlDTO1);
        expectedList.add(elvlDTO2);

        List<ElvlDTO> resultList = elvlService.getAllElvls();

        assertEquals(2, elvlService.getAllElvls().size());

        assertEquals(expectedList, resultList);

    }

    @Test
    void testGetElvlByIsin() {

        Float resultElvl = elvlService.getElvlByIsin("qwerty123456");
        assertEquals(100.1f, resultElvl);

    }

}
