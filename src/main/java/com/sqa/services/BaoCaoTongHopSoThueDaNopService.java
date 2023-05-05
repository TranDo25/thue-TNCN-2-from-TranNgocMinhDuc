package com.sqa.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqa.models.dtos.BaoCaoTongHopSoThueDaNopModel;
import com.sqa.models.dtos.BienLaiDongTienModel;
import com.sqa.models.dtos.ChiTietDongThueModel;
import com.sqa.models.dtos.HoadondongthueDTO;
import com.sqa.models.dtos.NguoinopthueDTO;
import com.sqa.models.entities.Chicucthue;
import com.sqa.models.entities.Hoadondongthue;
import com.sqa.models.entities.Nganhanghotrothanhtoan;
import com.sqa.models.entities.Nguoinopthue;
import com.sqa.models.entities.Taikhoanthanhtoan;
@Service
public class BaoCaoTongHopSoThueDaNopService {
	@Autowired
	private UserService userService;
	@Autowired
	private HoaDonDongThueService hddtService;
	@Autowired
	private TaiKhoanThanhToanService tkttService;
	@Autowired
	private ChiCucThueService cctService;
	@Autowired
	private NganHangHoTroThanhToanService nhService;

	public BaoCaoTongHopSoThueDaNopService(UserService userService, HoaDonDongThueService hddtService, TaiKhoanThanhToanService tkttService, ChiCucThueService cctService, NganHangHoTroThanhToanService nhService) {
		this.userService = userService;
		this.hddtService = hddtService;
		this.tkttService = tkttService;
		this.cctService = cctService;
		this.nhService = nhService;
	}

	public BaoCaoTongHopSoThueDaNopModel createBaoCaoTongHop(String userId, LocalDate startDate, LocalDate endDate) {
		Nguoinopthue user = userService.get(userId);
		NguoinopthueDTO user2 = userService.convertToDto(user);
		ArrayList<Hoadondongthue> dsHoaDon = new ArrayList<>(hddtService.getByUserId(userId, startDate, endDate));
		ArrayList<HoadondongthueDTO> dsHoaDonDTO = new ArrayList<>();
		for (Hoadondongthue i : dsHoaDon) {
			HoadondongthueDTO tmp = hddtService.convertToDto(i);
			dsHoaDonDTO.add(tmp);
		}
		for (HoadondongthueDTO i : dsHoaDonDTO) {
			Optional<Taikhoanthanhtoan> tmp = tkttService.get(i.getId_hoadondongthue());
			if (tmp.isPresent()) {
				i.setTaikhoanthanhtoanDTO(tkttService.convertToDto(tmp.get()));
			}
		}
		BaoCaoTongHopSoThueDaNopModel res = new BaoCaoTongHopSoThueDaNopModel(user2, dsHoaDonDTO);
		return res;
	}
	public ChiTietDongThueModel createChiTietDongThueModel(String id) {
		ChiTietDongThueModel tmp = new ChiTietDongThueModel();
		Optional<Hoadondongthue> hddt = hddtService.getHoaDonById(id);
		if (hddt.isPresent()) {
			Optional<Taikhoanthanhtoan> tktt = tkttService.getTKTTById(hddt.get().getId_taikhoanthanhtoan());
			if (tktt.isPresent()) {
				tmp.setTaiKhoanThanhToan(tktt.get());
				Optional<Nganhanghotrothanhtoan> nhhttt = nhService.getById(tktt.get().getNganhanghotrothanhtoanId());
				tmp.setNganHangHoTroThanhToan(nhhttt.get());
			}
			Optional<Chicucthue> cct = cctService.getCCTById(hddt.get().getId_coquanthue());
			if (tktt.isPresent()) {
				tmp.setChiCucThue(cct.get());
			}
		}
		tmp.setHoaDonDongThue(hddt.get());
		return tmp;
	}
	public BienLaiDongTienModel getBienLaiDongTien(String id, String userId) {
		BienLaiDongTienModel tmp = new BienLaiDongTienModel();
		tmp.setNguoiNopThue(userService.get(userId));
		Optional<Hoadondongthue> hddt = hddtService.getHoaDonById(id);
		if (hddt.isPresent()) {
			Optional<Taikhoanthanhtoan> tktt = tkttService.getTKTTById(hddt.get().getId_taikhoanthanhtoan());
			if (tktt.isPresent()) {
				tmp.setTaiKhoanThanhToan(tktt.get());
				Optional<Nganhanghotrothanhtoan> nhhttt = nhService.getById(tktt.get().getNganhanghotrothanhtoanId());
				tmp.setNganHangHoTroThanhToan(nhhttt.get());
			}
			Optional<Chicucthue> cct = cctService.getCCTById(hddt.get().getId_coquanthue());
			if (tktt.isPresent()) {
				tmp.setChiCucThue(cct.get());
			}
		}
		tmp.setHoaDonDongThue(hddt.get());
		return tmp;
	}
	
}
