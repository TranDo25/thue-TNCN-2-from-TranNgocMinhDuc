package com.sqa.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqa.models.dtos.TaikhoanthanhtoanDTO;
import com.sqa.models.entities.Taikhoanthanhtoan;
import com.sqa.repositories.TaiKhoanThanhToanRepo;

@Service
public class TaiKhoanThanhToanService {
	@Autowired
	private TaiKhoanThanhToanRepo tkttRepo;
	@Autowired
	private ModelMapper mapper;

	public Optional<Taikhoanthanhtoan> get(String id) {
		return tkttRepo.findById(id);
	}
	public TaikhoanthanhtoanDTO convertToDto(Taikhoanthanhtoan person) {
		TaikhoanthanhtoanDTO nntDTO = mapper.map(person, TaikhoanthanhtoanDTO.class);
		return nntDTO;
	}
	public Taikhoanthanhtoan convertToEntity(TaikhoanthanhtoanDTO personDto) {
		Taikhoanthanhtoan nnt = mapper.map(personDto, Taikhoanthanhtoan.class);
		return nnt;
	}
	public Optional<Taikhoanthanhtoan> getTKTTById(String id_taikhoanthanhtoan) {
		
		return tkttRepo.findById(id_taikhoanthanhtoan);
	}
}
