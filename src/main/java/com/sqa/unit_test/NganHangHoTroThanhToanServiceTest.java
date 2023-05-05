package com.sqa.unit_test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.sqa.services.NganHangHoTroThanhToanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sqa.models.entities.Nganhanghotrothanhtoan;
import com.sqa.repositories.NganHangHoTroThanhToanRepository;

@RunWith(MockitoJUnitRunner.class)
public class NganHangHoTroThanhToanServiceTest {

    @Mock
    private NganHangHoTroThanhToanRepository nhRepo;

    @InjectMocks
    private NganHangHoTroThanhToanService service;

    @Test
    public void testGetById() {
        // Tạo đối tượng Nganhanghotrothanhtoan giả để trả về từ repository mock
        Nganhanghotrothanhtoan nganHang = new Nganhanghotrothanhtoan();
        nganHang.setId("1");
        nganHang.setTennganhang("ACB");

        // Thiết lập hành vi của đối tượng mock
        when(nhRepo.findById("1")).thenReturn(Optional.of(nganHang));

        // Gọi phương thức cần kiểm tra và kiểm tra kết quả
        Optional<Nganhanghotrothanhtoan> result = service.getById("1");
        assertEquals("ACB", result.get().getTennganhang());
    }
}

