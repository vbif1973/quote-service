package com.epam.quoteservice.controller;

import com.epam.quoteservice.dto.ElvlDTO;
import com.epam.quoteservice.service.ElvlService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ElvlControllerTest {
    @InjectMocks
    ElvlController elvlController;
    @Mock
    ElvlService elvlService;

    @Test
    void testGetAllelvls() {
        // GIVEN
        List<ElvlDTO> elvlList = new ArrayList<>();
        ElvlDTO dto1 = new ElvlDTO("qwerty123456", 12.12f);
        ElvlDTO dto2 = new ElvlDTO("asdfgh123456", 25.25f);
        elvlList.add(dto1);
        elvlList.add(dto2);
        Mockito.when(elvlController.getAllElvls()).thenReturn(elvlList);
        // WHEN
        List<ElvlDTO> result = elvlController.getAllElvls();

        // THEN
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));
    }

    @Test
    void testGetElvlByIsin() {
        // GIVEN
        Mockito.when(elvlController.getElvlByIsin("qwerty123456")).thenReturn(12.12f);
        //WHEN
        Float result = elvlController.getElvlByIsin("qwerty123456");
        //THEN
        assertTrue(result.equals(12.12f));

    }
}