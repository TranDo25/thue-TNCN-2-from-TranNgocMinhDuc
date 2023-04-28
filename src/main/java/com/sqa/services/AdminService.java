package com.sqa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqa.models.entities.SystemAdmin;
import com.sqa.repositories.AdminRepository;

@Service
public class AdminService {
	@Autowired
	private AdminRepository _adminRepo;

	public SystemAdmin checkLogin(SystemAdmin admin) {
		SystemAdmin tmp = _adminRepo.kiemTraUsername(admin.getUsername());
		if (tmp != null) {
			if (admin.getPassword().equals(tmp.getPassword())) {
				return tmp;
			} else
				return null;
		} else
			return null;

	}

	public Optional<SystemAdmin> getById(int adminId) {
		
		return _adminRepo.findById(adminId);
	}
}
