package com.sqa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqa.models.entities.Nganhanghotrothanhtoan;
import com.sqa.repositories.NganHangHoTroThanhToanRepository;

@Service
public class NganHangHoTroThanhToanService {
	@Autowired
	private NganHangHoTroThanhToanRepository nhRepo;

	public Optional<Nganhanghotrothanhtoan> getById(String nganhanghotrothanhtoanId) {
		
		return nhRepo.findById(nganhanghotrothanhtoanId);
	}
	
}
