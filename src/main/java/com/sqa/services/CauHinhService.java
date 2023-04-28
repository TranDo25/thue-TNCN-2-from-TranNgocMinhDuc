package com.sqa.services;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqa.models.entities.Cauhinh;
import com.sqa.repositories.CauHinhRepository;

@Service
public class CauHinhService {
	@Autowired
	private CauHinhRepository _cauHinhRepo;

	public Cauhinh createNewCauHinh(int adminId) {
		int soLuongCauHinh = (int) _cauHinhRepo.count();
		String idCauHinh="";
		if(soLuongCauHinh<10) {
			idCauHinh+="CH00"+(soLuongCauHinh+1);
		}
		else idCauHinh+="CH0"+(soLuongCauHinh+1);
		// Lấy ra timestamp hiện tại
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		// Chuyển đổi sang kiểu Date
		Date date = new Date(timestamp.getTime());
		Cauhinh tmp = new Cauhinh(idCauHinh,date,adminId);
		return _cauHinhRepo.save(tmp);
	}

	public Cauhinh saveNewCauHinh(Cauhinh cauhinh) {
		return _cauHinhRepo.save(cauhinh);
		
	}

}
