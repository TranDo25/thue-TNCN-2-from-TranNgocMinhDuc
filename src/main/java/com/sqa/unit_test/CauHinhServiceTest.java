package com.sqa.unit_test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import com.sqa.services.CauHinhService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sqa.models.entities.Cauhinh;
import com.sqa.repositories.CauHinhRepository;

@RunWith(MockitoJUnitRunner.class)
public class CauHinhServiceTest {
    @Mock
    private CauHinhRepository _cauHinhRepo;

    @InjectMocks
    private CauHinhService service;

    @Before
    public void setUp() {
        ArrayList<Cauhinh> list = new ArrayList<Cauhinh>();
        Cauhinh cauhinh = new Cauhinh();
        cauhinh.setId_cauhinh("CH001");
        list.add(cauhinh);

        when(_cauHinhRepo.count()).thenReturn(1L);
        when(_cauHinhRepo.save(cauhinh)).thenReturn(cauhinh);
    }

    @Test
    public void testCreateNewCauHinh() {
        int adminId = 1;

        Cauhinh expectedCauHinh = new Cauhinh();
        expectedCauHinh.setId_cauhinh("CH002");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(timestamp.getTime());
        expectedCauHinh.setNgaysua(date);
        expectedCauHinh.setId_system_admin(adminId);

        when(_cauHinhRepo.save(expectedCauHinh)).thenReturn(expectedCauHinh);

        Cauhinh result = service.createNewCauHinh(adminId);

        assertEquals("CH002", result.getId_cauhinh());
        assertEquals(date, result.getNgaysua());
        assertEquals(adminId, result.getId_system_admin());
    }

    @Test
    public void testSaveNewCauHinh() {
        Cauhinh cauhinh = new Cauhinh();
        cauhinh.setId_cauhinh("CH001");

        when(_cauHinhRepo.save(cauhinh)).thenReturn(cauhinh);

        Cauhinh result = service.saveNewCauHinh(cauhinh);
        assertEquals("CH001", result.getId_cauhinh());
    }
}
