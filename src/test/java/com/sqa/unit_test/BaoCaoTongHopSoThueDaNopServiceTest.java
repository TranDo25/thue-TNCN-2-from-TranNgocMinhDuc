package com.sqa.unit_test;

import com.sqa.models.dtos.BaoCaoTongHopSoThueDaNopModel;
import com.sqa.models.dtos.ChiTietDongThueModel;
import com.sqa.models.dtos.HoadondongthueDTO;
import com.sqa.models.dtos.NguoinopthueDTO;
import com.sqa.models.entities.Chicucthue;
import com.sqa.models.entities.Hoadondongthue;
import com.sqa.models.entities.Nganhanghotrothanhtoan;
import com.sqa.models.entities.Taikhoanthanhtoan;
import com.sqa.services.BaoCaoTongHopSoThueDaNopService;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BaoCaoTongHopSoThueDaNopServiceTest {
    @Autowired
    private BaoCaoTongHopSoThueDaNopService _bcthService;

    @BeforeEach
    void setUp() {//làm việc gì đó trước khi chạy test
    }

    @AfterEach
    void tearDown() {//làm việc gì đó sau khi chạy test
    }

    @Test
    void createBaoCaoTongHop() throws ParseException {
        String dateString = "2023-03-25 16:06:02.0";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        Date parsedDate = dateFormat.parse(dateString);
        Timestamp timestamp = new Timestamp(parsedDate.getTime());
        NguoinopthueDTO nntDTO = new NguoinopthueDTO("NNT001", "034201005406", "domhkxtb", "dotrantb25@gmail.com",
                "Do5124160", timestamp, "8675380098", "Tran Xuan Do", "Thai Binh", "Chi cuc Thue khu vuc Tien Hai-Kien Xuong", "cccd", "0825124160",
                true, null, "Cao Đồng-Minh Quang-Kiến Xương-Thái Bình");
        ArrayList<HoadondongthueDTO> dsHoaDon = new ArrayList<>();
        String ngay_dong_tien = "2023-04-01";
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat2.parse(ngay_dong_tien);
        java.sql.Date ngay_dong_tien_sql = new java.sql.Date(date.getTime());
        dsHoaDon.add(new HoadondongthueDTO("LDT003", "31-04-2023", (Double) 1000000.0, "NNT001", "CCT001", ngay_dong_tien_sql, "TKTT001", null, (Double) 1000000.0));
        BaoCaoTongHopSoThueDaNopModel expectedResult = new BaoCaoTongHopSoThueDaNopModel(nntDTO, dsHoaDon);
        String dateString1 = "2023-04-01"; // chuỗi cần chuyển đổi
        LocalDate startDate = LocalDate.parse(dateString1); // tạo đối tượng LocalDate từ chuỗi
        String dateString2 = "2023-04-01"; // chuỗi cần chuyển đổi
        LocalDate endDate = LocalDate.parse(dateString2); // tạo đối tượng LocalDate từ chuỗi
        String userId = "NNT001";


        BaoCaoTongHopSoThueDaNopModel actualResult = _bcthService.createBaoCaoTongHop(userId, startDate, endDate);
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void createChiTietDongThueModel() throws ParseException {
        String ngay_dong_tien = "2023-04-01";
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat2.parse(ngay_dong_tien);
        java.sql.Date ngay_dong_tien_sql = new java.sql.Date(date.getTime());
        Hoadondongthue hoaDonDonThue = new Hoadondongthue(
                  "LDT003",
                  "31-04-2023",
                (Double) 1000000.0,
                "NNT001",
                "CCT001",
                ngay_dong_tien_sql,
                "TKTT001",
                (Double)1000000.0
        );
        Chicucthue chicucthue = new Chicucthue(
                "CCT001","Chi cuc thue khu vuc Kien Xuong-Tien Hai"," Số 211 phố Hùng Thắng - Thị trấn Tiền Hải - Huyện Tiền Hải-tỉnh Thái Bình",
                " ",null,"Thái Bình","190010078888"
        );
        String ngay_hieu_luc = "2024-12-19";

        Date date2 = dateFormat2.parse(ngay_hieu_luc);
        java.sql.Date ngay_hieu_luc_sql = new java.sql.Date(date2.getTime());
        Taikhoanthanhtoan taikhoanthanhtoan = new Taikhoanthanhtoan(
                "TKTT001","Tran Xuan Do",ngay_hieu_luc_sql,"0691000434963","NNT001","NHHTTT001"
        );
        Nganhanghotrothanhtoan nganhanghotrothanhtoan = new Nganhanghotrothanhtoan(
                "NHHTTT001","Vietcombank"
        );
        ChiTietDongThueModel expected = new ChiTietDongThueModel(hoaDonDonThue, chicucthue, taikhoanthanhtoan, nganhanghotrothanhtoan);
        ChiTietDongThueModel actual = _bcthService.createChiTietDongThueModel("LDT003");
        assertEquals(expected,actual);
    }

    @Test
    void getBienLaiDongTien() {
    }
}