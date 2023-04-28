package com.sqa.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sqa.models.dtos.BaoCaoTongHopSoThueDaNopModel;
import com.sqa.models.dtos.HoadondongthueDTO;
import com.sqa.models.entities.Hoadondongthue;
import com.sqa.repositories.HoaDonDongThueRepository;

@Service
public class HoaDonDongThueService {
	@Autowired
	private HoaDonDongThueRepository hddtRepo;
	@Autowired
	private ModelMapper mapper;

	public List<Hoadondongthue> getByUserId(String userId, LocalDate startDate, LocalDate endDate) {
		
		java.sql.Date sql_start_date =  java.sql.Date.valueOf(startDate);
		java.sql.Date sql_end_date =  java.sql.Date.valueOf(endDate);

		return hddtRepo.findAllByUserIdByRangeDate(userId, sql_start_date, sql_end_date);
	}
	public HoadondongthueDTO convertToDto(Hoadondongthue person) {
		HoadondongthueDTO nntDTO = mapper.map(person, HoadondongthueDTO.class);
		return nntDTO;
	}
	public Hoadondongthue convertToEntity(HoadondongthueDTO personDto) {
		Hoadondongthue nnt = mapper.map(personDto, Hoadondongthue.class);
		return nnt;
	}
	public Double calculateTotalTaxHaveToPay(BaoCaoTongHopSoThueDaNopModel res) {
		double kq = 0;
		for(HoadondongthueDTO i: res.getDsHoaDon()) {
			kq+= i.getSotienphaidong();
		}
		return kq;
	}
	public Double calculateTotalTaxPaid(BaoCaoTongHopSoThueDaNopModel res) {
		double kq = 0;
		for(HoadondongthueDTO i: res.getDsHoaDon()) {
			kq+= i.getSotiendadong();
		}
		return kq;
	}
	public Optional<Hoadondongthue> getHoaDonById(String id) {
		
		return hddtRepo.findById(id);
	}
	

}
