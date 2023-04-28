package com.sqa.unit_test;

import com.sqa.models.dtos.*;
import com.sqa.models.entities.*;
import com.sqa.services.*;
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

@RunWith(MockitoJUnitRunner.class)
public class BaoCaoTongHopSoThueDaNopServiceTest {

    @InjectMocks
    private BaoCaoTongHopSoThueDaNopService baoCaoService;

    @Mock
    private UserService userService;

    @Mock
    private HoaDonDongThueService hoaDonService;

    @Mock
    private TaiKhoanThanhToanService taiKhoanService;

    @Mock
    private ChiCucThueService chiCucService;

    @Mock
    private NganHangHoTroThanhToanService nganHangService;

    @Test
    public void createBaoCaoTongHop_WithValidInput_ReturnsExpectedResult() {
        // Arrange
        String userId = "123";
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 12, 31);

        Nguoinopthue user = new Nguoinopthue();
        user.setId(userId);
        Mockito.when(userService.get(userId)).thenReturn(user);

        List<Hoadondongthue> hoaDons = Arrays.asList(
                new Hoadondongthue(),
                new Hoadondongthue(),
                new Hoadondongthue()
        );
        Mockito.when(hoaDonService.getByUserId(userId, startDate, endDate)).thenReturn(hoaDons);

        BaoCaoTongHopSoThueDaNopModel expected = new BaoCaoTongHopSoThueDaNopModel(new NguoinopthueDTO(user), new ArrayList<>());
        for (Hoadondongthue hoaDon : hoaDons) {
            expected.getDsHoaDon().add(new HoadondongthueDTO(hoaDon));
        }

        // Act
        BaoCaoTongHopSoThueDaNopModel actual = baoCaoService.createBaoCaoTongHop(userId, startDate, endDate);

        // Assert
//        Assert.assertEquals(expected, actual);
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void createChiTietDongThueModel_WithValidInput_ReturnsExpectedResult() {
        // Arrange
        String id = "123";

        Hoadondongthue hoaDon = new Hoadondongthue();
        hoaDon.setId_coquanthue(id);
        Mockito.when(hoaDonService.getHoaDonById(id)).thenReturn(Optional.of(hoaDon));

        Taikhoanthanhtoan taiKhoan = new Taikhoanthanhtoan();
        Mockito.when(taiKhoanService.getTKTTById(hoaDon.getId_taikhoanthanhtoan())).thenReturn(Optional.of(taiKhoan));

        Nganhanghotrothanhtoan nganHang = new Nganhanghotrothanhtoan();
        Mockito.when(nganHangService.getById(taiKhoan.getNganhanghotrothanhtoanId())).thenReturn(Optional.of(nganHang));

        Chicucthue chiCuc = new Chicucthue();
        Mockito.when(chiCucService.getCCTById(hoaDon.getId_coquanthue())).thenReturn(Optional.of(chiCuc));

        ChiTietDongThueModel expected = new ChiTietDongThueModel();
        expected.setHoaDonDongThue(new Hoadondongthue(hoaDon));
        expected.setTaiKhoanThanhToan(taiKhoan);
        expected.setNganHangHoTroThanhToan(nganHang);
        expected.setChiCucThue(chiCuc);

        // Act
        ChiTietDongThueModel actual = baoCaoService.createChiTietDongThueModel(id);

        // Assert
//        Assert.assertEquals(expected, actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getBienLaiDongTien_WithValidInput_ReturnsExpectedResult() {
        // Arrange
        String id = "123";
        String userId = "456";

        Nguoinopthue nguoiNopThue = new Nguoinopthue();
        Mockito.when(userService.get(userId)).thenReturn(nguoiNopThue);

        Hoadondongthue hoaDon = new Hoadondongthue();
        hoaDon.setId_coquanthue(id);
        hoaDon.setId_taikhoanthanhtoan("789");
        hoaDon.setId_coquanthue("101112");
        Mockito.when(hoaDonService.getHoaDonById(id)).thenReturn(Optional.of(hoaDon));

        Taikhoanthanhtoan taiKhoan = new Taikhoanthanhtoan();
        Mockito.when(taiKhoanService.getTKTTById("789")).thenReturn(Optional.of(taiKhoan));

        Nganhanghotrothanhtoan nganHang = new Nganhanghotrothanhtoan();
        Mockito.when(nganHangService.getById(taiKhoan.getNganhanghotrothanhtoanId())).thenReturn(Optional.of(nganHang));

        Chicucthue chiCuc = new Chicucthue();
        Mockito.when(chiCucService.getCCTById("101112")).thenReturn(Optional.of(chiCuc));

        BienLaiDongTienModel expected = new BienLaiDongTienModel();
        expected.setNguoiNopThue(nguoiNopThue);
        expected.setHoaDonDongThue(new Hoadondongthue(hoaDon));
        expected.setTaiKhoanThanhToan(taiKhoan);
        expected.setNganHangHoTroThanhToan(nganHang);
        expected.setChiCucThue(chiCuc);

        // Act
        BienLaiDongTienModel actual = baoCaoService.getBienLaiDongTien(id, userId);

        // Assert
//        Assert.assertEquals(expected, actual);
        Assertions.assertEquals(expected, actual);
    }
}
