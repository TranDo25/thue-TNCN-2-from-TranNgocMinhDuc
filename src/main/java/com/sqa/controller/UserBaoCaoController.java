package com.sqa.controller;

import com.sqa.models.dtos.BaoCaoTongHopSoThueDaNopModel;
import com.sqa.models.dtos.BienLaiDongTienModel;
import com.sqa.models.dtos.ChiTietDongThueModel;
import com.sqa.services.BaoCaoTongHopSoThueDaNopService;
import com.sqa.services.HoaDonDongThueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/report")
public class UserBaoCaoController {

    @Autowired
    private HoaDonDongThueService hddtService;

    @Autowired
    private BaoCaoTongHopSoThueDaNopService _bcthService;

    @GetMapping("/baoCaoTongHopSoThueDaNop")
    public String BaoCaoTongHopSoThueDaNop(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @CookieValue(value = "userId", defaultValue = "-1") String userId, Model model) {
        BaoCaoTongHopSoThueDaNopModel res = _bcthService.createBaoCaoTongHop(userId, startDate, endDate);
        Double tongSoTienPhaiDong = hddtService.calculateTotalTaxHaveToPay(res);
        Double tongSoTienDaDong = hddtService.calculateTotalTaxPaid(res);
        model.addAttribute("res", res);
        model.addAttribute("tong_tien_phai_dong", tongSoTienPhaiDong);
        model.addAttribute("tong_tien_da_dong", tongSoTienDaDong);
        model.addAttribute("tong_tien_con_no", tongSoTienPhaiDong - tongSoTienDaDong);
        return "./BaoCao/bao_cao_tong_hop_so_thue_da_nop";
    }

    @GetMapping("/chiTietHoaDonNopTien")
    public String ChiTietHoaDonNopTien(Model model) {
        return "./BaoCao/chi_tiet_giay_nop_tien";
    }

    @GetMapping("/chiTietHoaDonNopTien/{id}")
    public String ChiTietCacLanDongThue(@PathVariable("id") String id, Model model) {
        ChiTietDongThueModel tmp = _bcthService.createChiTietDongThueModel(id);
        model.addAttribute("obj", tmp);
        return "./BaoCao/chi_tiet_cac_lan_dong";
    }

    @GetMapping("/bienlaidongtien/{id}")
    public String XemBienLaiDongTien(@PathVariable("id") String id,
                                     @CookieValue(value = "userId", defaultValue = "-1") String userId, Model model) {

        BienLaiDongTienModel tmp = _bcthService.getBienLaiDongTien(id, userId);
        model.addAttribute("obj", tmp);
        return "./BaoCao/chi_tiet_giay_nop_tien";
    }


    @GetMapping("/chooseRangeTime")
    public String ChooseRangeTime(Model model) {
        return "./BaoCao/chon_khoang_thoi_gian";
    }

}
