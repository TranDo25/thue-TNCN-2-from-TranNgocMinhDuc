package com.sqa.unit_test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import com.sqa.services.BieuThueToanPhanService;
import com.sqa.services.CauHinhService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sqa.models.entities.Bieuthuetoanphan;
import com.sqa.models.entities.Cauhinh;
import com.sqa.repositories.BieuThueToanPhanRepository;

@RunWith(MockitoJUnitRunner.class)
public class BieuThueToanPhanServiceTest {
    @Mock
    private BieuThueToanPhanRepository _bttpRepo;

    @Mock
    private CauHinhService cauHinhService;

    @InjectMocks
    private BieuThueToanPhanService service;

    @Before
    public void setUp() {
        ArrayList<Bieuthuetoanphan> list = new ArrayList<Bieuthuetoanphan>();
        Bieuthuetoanphan bttp = new Bieuthuetoanphan();
        bttp.setId_bieuthuetoanphan("BTTP001");
        list.add(bttp);

        when(_bttpRepo.findAll()).thenReturn(list);
        when(_bttpRepo.count()).thenReturn(1L);

        Cauhinh cauhinh = new Cauhinh();
        cauhinh.setId_cauhinh("1");
        when(cauHinhService.createNewCauHinh(1)).thenReturn(cauhinh);
    }

    @Test
    public void testGet() {
        ArrayList<Bieuthuetoanphan> result = service.get();

        assertEquals(1, result.size());
        assertEquals("BTTP001", result.get(0).getId_bieuthuetoanphan());
    }

    @Test
    public void testAddNewMucThue() {
        Bieuthuetoanphan bttp = new Bieuthuetoanphan();
        bttp.setId_bieuthuetoanphan("BTTP002");

        when(_bttpRepo.save(bttp)).thenReturn(bttp);

        Bieuthuetoanphan result = service.addNewMucThue(bttp);
        assertEquals("BTTP002", result.getId_bieuthuetoanphan());
    }

    @Test
    public void testCreateID() {
        String result = service.createID();
        assertEquals("BTTP002", result);
    }

    @Test
    public void testGetBTTPById() {
        String id = "BTTP001";

        Bieuthuetoanphan bttp = new Bieuthuetoanphan();
        bttp.setId_bieuthuetoanphan(id);

        when(_bttpRepo.findById(id)).thenReturn(Optional.of(bttp));

        Optional<Bieuthuetoanphan> result = service.getBTTPById(id);
        assertEquals("BTTP001", result.get().getId_bieuthuetoanphan());
    }

    @Test
    public void testSave() {
        Bieuthuetoanphan bttp = new Bieuthuetoanphan();
        bttp.setId_bieuthuetoanphan("BTTP002");

        when(_bttpRepo.save(bttp)).thenReturn(bttp);

        Bieuthuetoanphan result = service.save(bttp);
        assertEquals("BTTP002", result.getId_bieuthuetoanphan());
    }

    @Test
    public void testDeleteById() {
        String id = "BTTP001";

        doNothing().when(_bttpRepo).deleteById(id);

        boolean result = service.deleteById(id);
        assertEquals(true, result);
    }

    @Test
    public void testThemMucThueToanPhan() {
        String adminIdtmp = "1";

        Bieuthuetoanphan bttp = new Bieuthuetoanphan();
        bttp.setThunhaptinhthue("100");

        Cauhinh cauhinh = new Cauhinh();
        cauhinh.setId_cauhinh("1");

        when(cauHinhService.createNewCauHinh(1)).thenReturn(cauhinh);
        when(_bttpRepo.save(bttp)).thenReturn(bttp);

        Bieuthuetoanphan result = service.themMucThueToanPhan(adminIdtmp, bttp);

        assertEquals("BTTP002", result.getId_bieuthuetoanphan());
        assertEquals("100", result.getThunhaptinhthue());
        assertEquals("1", result.getId_cauhinh());
    }
}
