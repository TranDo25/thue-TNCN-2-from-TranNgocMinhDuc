package com.sqa.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqa.models.entities.Bieuthuetoanphan;
import com.sqa.models.entities.Cauhinh;
import com.sqa.repositories.BieuThueToanPhanRepository;

@Service
public class BieuThueToanPhanService {
	@Autowired
	private BieuThueToanPhanRepository _bttpRepo;
	@Autowired
	private CauHinhService cauHinhService;

	public ArrayList<Bieuthuetoanphan> get() {
		return (ArrayList<Bieuthuetoanphan>) _bttpRepo.findAll();		
	}
	public Bieuthuetoanphan addNewMucThue(Bieuthuetoanphan bttp) {
		return _bttpRepo.save(bttp);
	}
	public String createID() {
		int somucthue = (int) _bttpRepo.count();
		String res = "BTTP";
		if(somucthue <10) {
			res+="00"+(somucthue+1);
		}else res +="0"+(somucthue+1);
		return res;
	}
	public Optional<Bieuthuetoanphan> getBTTPById(String id){
		return _bttpRepo.findById(id);
	}
	public Bieuthuetoanphan save(Bieuthuetoanphan bttp) {
		return _bttpRepo.save(bttp);
	}
	public boolean deleteById(String idMucThue) {
		try {
			_bttpRepo.deleteById(idMucThue);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	public Bieuthuetoanphan themMucThueToanPhan(String adminIdtmp, Bieuthuetoanphan bttp) {
		int adminId = Integer.parseInt(adminIdtmp);
		Cauhinh cauhinh = cauHinhService.createNewCauHinh(adminId);
		bttp.setId_cauhinh(cauhinh.getId_cauhinh());
		String id_bieuthuetoanphan = createID();
		bttp.setId_bieuthuetoanphan(id_bieuthuetoanphan);
		Bieuthuetoanphan res = (Bieuthuetoanphan) addNewMucThue(bttp);
		return res;
	}
}
