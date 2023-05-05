package com.sqa.unit_test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.sqa.services.TaiKhoanThanhToanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sqa.models.entities.Taikhoanthanhtoan;
import com.sqa.repositories.TaiKhoanThanhToanRepo;

@RunWith(MockitoJUnitRunner.class)
public class TaiKhoanThanhToanServiceTest {

    @Mock
    private TaiKhoanThanhToanRepo tkttRepo;

    @InjectMocks
    private TaiKhoanThanhToanService service;

    @Test
    public void testGetTKTTById() {
        // Tạo đối tượng Taikhoanthanhtoan giả để trả về từ repository mock
        Taikhoanthanhtoan tktt = new Taikhoanthanhtoan();
        tktt.setId("1");
        tktt.setSothe("123456789");

        // Thiết lập hành vi của đối tượng mock
        when(tkttRepo.findById("1")).thenReturn(Optional.of(tktt));

        // Gọi phương thức cần kiểm tra và kiểm tra kết quả
        Optional<Taikhoanthanhtoan> result = service.getTKTTById("1");
        assertEquals("123456789", result.get().getSothe());
    }
}

