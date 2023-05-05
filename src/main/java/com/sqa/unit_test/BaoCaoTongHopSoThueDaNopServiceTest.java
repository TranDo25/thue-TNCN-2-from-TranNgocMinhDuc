package com.sqa.unit_test;

import com.sqa.models.dtos.*;
import com.sqa.models.entities.*;
import com.sqa.services.*;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BaoCaoTongHopSoThueDaNopServiceTest {
    @Mock
    private UserService userService;
    @Mock
    private HoaDonDongThueService hddtService;
    @Mock
    private TaiKhoanThanhToanService tkttService;
    @Mock
    private ChiCucThueService cctService;
    @Mock
    private NganHangHoTroThanhToanService nhService;

    private BaoCaoTongHopSoThueDaNopService baoCaoService;

    @Before
    public void setUp() {
        baoCaoService = new BaoCaoTongHopSoThueDaNopService(userService, hddtService, tkttService, cctService, nhService);
    }

    @Test
    public void createBaoCaoTongHop_ReturnsCorrectModel() {
        // Arrange
        String userId = "1";
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 12, 31);

        Nguoinopthue user = new Nguoinopthue();
        user.setId(userId);
        user.setUsername("user1");
        user.setPassword("pass1");

        Hoadondongthue hddt = new Hoadondongthue();
        hddt.setId("1");
        hddt.setId_taikhoanthanhtoan("1");

        Taikhoanthanhtoan tktt = new Taikhoanthanhtoan();
        tktt.setId("1");
        tktt.setNganhanghotrothanhtoanId("1");

        Nganhanghotrothanhtoan nhhttt = new Nganhanghotrothanhtoan();
        nhhttt.setId("1");
        nhhttt.setTennganhang("Bank A");

        Chicucthue cct = new Chicucthue();
        cct.setId_chicucthue("1");
        cct.setTen("Chi Cuc A");

        ArrayList<Hoadondongthue> dsHoaDon = new ArrayList<>();
        dsHoaDon.add(hddt);

        when(userService.get(userId)).thenReturn(user);
        when(hddtService.getByUserId(userId, startDate, endDate)).thenReturn(dsHoaDon);
        when(hddtService.convertToDto(hddt)).thenReturn(new HoadondongthueDTO());
        when(tkttService.get(hddt.getId_taikhoanthanhtoan())).thenReturn(Optional.of(tktt));
        when(nhService.getById(tktt.getNganhanghotrothanhtoanId())).thenReturn(Optional.of(nhhttt));
        when(cctService.getCCTById(hddt.getId_coquanthue())).thenReturn(Optional.of(cct));

        // Act
        BaoCaoTongHopSoThueDaNopModel result = baoCaoService.createBaoCaoTongHop(userId, startDate, endDate);

        // Assert
        assertNotNull(result);
        assertEquals(user.getId(), result.getNntDTO().getId());
        assertNotNull(result.getDsHoaDon());
        assertEquals(1, result.getDsHoaDon().size());
    }

    @Test
    public void createChiTietDongThueModel_ReturnsCorrectModel() {
        // Arrange
        String hddtId = "1";

        Hoadondongthue hddt = new Hoadondongthue();
        hddt.setId(hddtId);
        hddt.setId_taikhoanthanhtoan("1");
        hddt.setId_coquanthue("1");

        Taikhoanthanhtoan tktt = new Taikhoanthanhtoan();
        tktt.setId("1");
        tktt.setNganhanghotrothanhtoanId("1");

        Nganhanghotrothanhtoan nhhttt = new Nganhanghotrothanhtoan();
        nhhttt.setId("1");
        nhhttt.setTennganhang("Bank A");

        Chicucthue cct = new Chicucthue();
        cct.setId_chicucthue("1");
        cct.setTen("Chi Cuc A");

        when(hddtService.getHoaDonById(hddtId)).thenReturn(Optional.of(hddt));
        when(tkttService.getTKTTById(hddt.getId_taikhoanthanhtoan())).thenReturn(Optional.of(tktt));
        when(nhService.getById(tktt.getNganhanghotrothanhtoanId())).thenReturn(Optional.of(nhhttt));
        when(cctService.getCCTById(hddt.getId_coquanthue())).thenReturn(Optional.of(cct));
        // Act
        ChiTietDongThueModel result = baoCaoService.createChiTietDongThueModel(hddtId);

        // Assert
        assertNotNull(result);
        assertEquals(hddtId, result.getHoaDonDongThue().getId_coquanthue());
        assertNotNull(result.getTaiKhoanThanhToan());
        assertNotNull(result.getNganHangHoTroThanhToan());
        assertNotNull(result.getChiCucThue());
    }

    @Test
    public void getBienLaiDongTien_ReturnsCorrectModel() {
        // Arrange
        String hddtId = "1";
        String userId = "1";

        Nguoinopthue user = new Nguoinopthue();
        user.setId(userId);
        user.setUsername("user1");
        user.setPassword("pass1");

        Hoadondongthue hddt = new Hoadondongthue();
        hddt.setId(hddtId);
        hddt.setId_taikhoanthanhtoan("1");
        hddt.setId_coquanthue("1");

        Taikhoanthanhtoan tktt = new Taikhoanthanhtoan();
        tktt.setId("1");
        tktt.setNganhanghotrothanhtoanId("1");

        Nganhanghotrothanhtoan nhhttt = new Nganhanghotrothanhtoan();
        nhhttt.setId("1");
        nhhttt.setTennganhang("Bank A");

        Chicucthue cct = new Chicucthue();
        cct.setId_chicucthue("1");
        cct.setTen("Chi Cuc A");

        when(userService.get(userId)).thenReturn(user);
        when(hddtService.getHoaDonById(hddtId)).thenReturn(Optional.of(hddt));
        when(tkttService.getTKTTById(hddt.getId_taikhoanthanhtoan())).thenReturn(Optional.of(tktt));
        when(nhService.getById(tktt.getNganhanghotrothanhtoanId())).thenReturn(Optional.of(nhhttt));
        when(cctService.getCCTById(hddt.getId_coquanthue())).thenReturn(Optional.of(cct));

        // Act
        BienLaiDongTienModel result = baoCaoService.getBienLaiDongTien(hddtId, userId);

        // Assert
        assertNotNull(result);
        assertEquals(userId, result.getNguoiNopThue().getId());
        assertEquals(hddtId, result.getHoaDonDongThue().getId_hoadondongthue());
        assertNotNull(result.getTaiKhoanThanhToan());
        assertNotNull(result.getNganHangHoTroThanhToan());
        assertNotNull(result.getChiCucThue());
    }
}