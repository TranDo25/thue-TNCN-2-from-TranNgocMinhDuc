package com.sqa.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqa.models.entities.Bieuthueluytientungphan;
import com.sqa.models.entities.Cauhinh;
import com.sqa.repositories.BieuThueLuyTienTungPhanRepository;

@Service
public class BieuThueLuyTienTungPhanService {
	@Autowired
	private BieuThueLuyTienTungPhanRepository btltRepo;
	@Autowired
	private CauHinhService cauHinhService;
	public ArrayList<Bieuthueluytientungphan> get() {
		return btltRepo.getTheDefaultVersionConfig();
	}

	public Bieuthueluytientungphan addNewMucThue(Bieuthueluytientungphan btlt) {
		return btltRepo.save(btlt);
	}

	public String createID() {
		int somucthue = (int) btltRepo.count();
		String res = "BTLT";
		if (somucthue < 10) {
			res += "00" + (somucthue + 1);
		} else
			res += "0" + (somucthue + 1);
		return res;
	}

	public Optional<Bieuthueluytientungphan> getBTLTById(String id) {

		return btltRepo.findById(id);
	}

	public Bieuthueluytientungphan save(Bieuthueluytientungphan btlt3) {

		return btltRepo.save(btlt3);
	}

	public boolean deleteById(String idMucThue) {

		try {
			btltRepo.deleteById(idMucThue);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Bieuthueluytientungphan themMucThueLuyTien(String adminIdtmp, Bieuthueluytientungphan btlt) {
		int adminId = Integer.parseInt(adminIdtmp);
		Cauhinh cauhinh = cauHinhService.createNewCauHinh(adminId);

		btlt.setId_cauhinh(cauhinh.getId_cauhinh());
		String id_bieuthueluytientungphan = createID();
		btlt.setId_bieuthueluytientungphan(id_bieuthueluytientungphan);
		Bieuthueluytientungphan res = (Bieuthueluytientungphan) addNewMucThue(btlt);
		return res;
	}

}
