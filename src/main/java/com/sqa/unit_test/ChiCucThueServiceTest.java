package com.sqa.unit_test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.sqa.services.ChiCucThueService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sqa.models.entities.Chicucthue;
import com.sqa.repositories.ChiCucThueRepository;

@RunWith(MockitoJUnitRunner.class)
public class ChiCucThueServiceTest {
    @Mock
    private ChiCucThueRepository _cctRepo;

    @InjectMocks
    private ChiCucThueService service;

    @Test
    public void testGetCCTById() {
        String id = "CCT001";

        Chicucthue cct = new Chicucthue();
        cct.setId_chicucthue(id);

        when(_cctRepo.findById(id)).thenReturn(Optional.of(cct));

        Optional<Chicucthue> result = service.getCCTById(id);
        assertEquals("CCT001", result.get().getId_chicucthue());
    }
}

