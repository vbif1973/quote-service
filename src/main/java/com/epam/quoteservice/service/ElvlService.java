package com.epam.quoteservice.service;

import com.epam.quoteservice.dto.ElvlDTO;

import java.util.List;

public interface ElvlService {

    List<ElvlDTO> getAllElvls();
    Float getElvlByIsin(String isin);

}
