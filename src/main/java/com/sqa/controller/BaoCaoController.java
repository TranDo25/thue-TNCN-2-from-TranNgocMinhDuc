package com.sqa.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sqa.models.entities.Nguoinopthue;
import com.sqa.models.entities.Taikhoanthanhtoan;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqa.models.dtos.BaoCaoTongHopSoThueDaNopModel;
import com.sqa.models.dtos.BienLaiDongTienModel;
import com.sqa.models.dtos.ChiTietDongThueModel;
import com.sqa.models.dtos.HoadondongthueDTO;
import com.sqa.models.dtos.NguoinopthueDTO;
import com.sqa.models.dtos.TaikhoanthanhtoanDTO;
import com.sqa.models.entities.Chicucthue;
import com.sqa.models.entities.Hoadondongthue;
import com.sqa.models.entities.Nganhanghotrothanhtoan;
import com.sqa.services.BaoCaoTongHopSoThueDaNopService;
import com.sqa.services.ChiCucThueService;
import com.sqa.services.HoaDonDongThueService;
import com.sqa.services.NganHangHoTroThanhToanService;
import com.sqa.services.TaiKhoanThanhToanService;
import com.sqa.services.UserService;

@Controller
@RequestMapping("/report")
public class BaoCaoController {
	
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
		return "./bao_cao/baoCaoTongHopSoThueDaNop";
	}

	@GetMapping("/chiTietHoaDonNopTien")
	public String ChiTietHoaDonNopTien(Model model) {
		return "./bao_cao/chi_tiet_giay_nop_tien";
	}

	@GetMapping("/chiTietHoaDonNopTien/{id}")
	public String ChiTietCacLanDongThue(@PathVariable("id") String id, Model model) {
		ChiTietDongThueModel tmp = _bcthService.createChiTietDongThueModel(id);
		model.addAttribute("obj", tmp);
		return "./bao_cao/chi_tiet_cac_lan_dong";
	}

	@GetMapping("/bienlaidongtien/{id}")
	public String XemBienLaiDongTien(@PathVariable("id") String id,
			@CookieValue(value = "userId", defaultValue = "-1") String userId, Model model) {
	
		BienLaiDongTienModel tmp = _bcthService.getBienLaiDongTien(id, userId);
		model.addAttribute("obj", tmp);
		return "./bao_cao/chi_tiet_giay_nop_tien";
	}


	@GetMapping("/chooseRangeTime")
	public String ChooseRangeTime(Model model) {
		return "./bao_cao/chon_khoang_thoi_gian";
	}

}
