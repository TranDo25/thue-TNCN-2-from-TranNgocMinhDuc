package com.sqa.unit_test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

import com.sqa.services.BieuThueLuyTienTungPhanService;
import com.sqa.services.CauHinhService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sqa.models.entities.Bieuthueluytientungphan;
import com.sqa.models.entities.Cauhinh;
import com.sqa.repositories.BieuThueLuyTienTungPhanRepository;

@RunWith(MockitoJUnitRunner.class)
public class BieuThueLuyTienTungPhanServiceTest {

    @Mock
    private BieuThueLuyTienTungPhanRepository btltRepo;

    @Mock
    private CauHinhService cauHinhService;

    @InjectMocks
    private BieuThueLuyTienTungPhanService service;

    @Before
    public void setUp() {
        ArrayList<Bieuthueluytientungphan> list = new ArrayList<Bieuthueluytientungphan>();
        Bieuthueluytientungphan btlt = new Bieuthueluytientungphan();
        btlt.setId_bieuthueluytientungphan("BTLT001");
        list.add(btlt);

        when(btltRepo.getTheDefaultVersionConfig()).thenReturn(list);
        when(btltRepo.count()).thenReturn(1L);

        Cauhinh cauhinh = new Cauhinh();
        cauhinh.setId_cauhinh("1");
        when(cauHinhService.createNewCauHinh(1)).thenReturn(cauhinh);
    }

    @Test
    public void testGet() {
        ArrayList<Bieuthueluytientungphan> result = service.get();

        assertEquals(1, result.size());
        assertEquals("BTLT001", result.get(0).getId_bieuthueluytientungphan());
    }

    @Test
    public void testAddNewMucThue() {
        Bieuthueluytientungphan btlt = new Bieuthueluytientungphan();
        btlt.setId_bieuthueluytientungphan("BTLT002");

        when(btltRepo.save(btlt)).thenReturn(btlt);

        Bieuthueluytientungphan result = service.addNewMucThue(btlt);

        assertEquals("BTLT002", result.getId_bieuthueluytientungphan());
    }

    @Test
    public void testCreateID() {
        String result = service.createID();

        assertEquals("BTLT002", result);
    }

    @Test
    public void testGetBTLTById() {
        String id = "BTLT001";

        Bieuthueluytientungphan btlt = new Bieuthueluytientungphan();
        btlt.setId_bieuthueluytientungphan(id);

        when(btltRepo.findById(id)).thenReturn(Optional.of(btlt));

        Optional<Bieuthueluytientungphan> result = service.getBTLTById(id);

        assertEquals("BTLT001", result.get().getId_bieuthueluytientungphan());
    }

    @Test
    public void testSave() {
        Bieuthueluytientungphan btlt = new Bieuthueluytientungphan();
        btlt.setId_bieuthueluytientungphan("BTLT002");

        when(btltRepo.save(btlt)).thenReturn(btlt);

        Bieuthueluytientungphan result = service.save(btlt);

        assertEquals("BTLT002", result.getId_bieuthueluytientungphan());
    }

    @Test
    public void testDeleteById() {
        String id = "BTLT001";

        doNothing().when(btltRepo).deleteById(id);

        boolean result = service.deleteById(id);

        assertEquals(true, result);
    }

    @Test
    public void testThemMucThueLuyTien() {
        String adminIdtmp = "1";
        Bieuthueluytientungphan btlt = new Bieuthueluytientungphan();
        btlt.setPhanthunhaptinhthuethang("100");

        Cauhinh cauhinh = new Cauhinh();
        cauhinh.setId_cauhinh("1");

        when(cauHinhService.createNewCauHinh(1)).thenReturn(cauhinh);
        when(btltRepo.save(btlt)).thenReturn(btlt);

        Bieuthueluytientungphan result = service.themMucThueLuyTien(adminIdtmp, btlt);

        assertEquals("BTLT002", result.getId_bieuthueluytientungphan());
        assertEquals("100", result.getPhanthunhaptinhthuethang());
        assertEquals("1", result.getId_cauhinh());
    }
}
