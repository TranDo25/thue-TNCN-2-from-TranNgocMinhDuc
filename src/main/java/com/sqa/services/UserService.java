package com.sqa.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.sqa.models.dtos.NguoinopthueDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


import com.sqa.models.entities.Nguoinopthue;
import com.sqa.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class UserService {

	@Autowired 
	private UserRepository repository;
	@Autowired
	private final ModelMapper mapper;
	public UserService() {
		this.mapper = new ModelMapper();
	}
	
	public List<Nguoinopthue> listAllUser() {
		return repository.findAll();
	}
	
	public void save(Nguoinopthue user) {
		repository.save(user);
	}
	
	public Nguoinopthue get(String id) {
		return repository.findById(id).get();
	}
	public void delete(String id) {
		repository.deleteById(id);
	}
	public boolean checkLogin(Nguoinopthue user) {
		List<Nguoinopthue> list = repository.findAll();
		if (list != null) {
			for (Nguoinopthue i : list) {
				String username = i.getUsername();
				String password = i.getPassword();
				if (username != null && password != null && username.compareTo(user.getUsername()) == 0 && password.compareTo(user.getPassword()) == 0) {
					user.setId(i.getId());
					user.setTencanhan(i.getTencanhan());
					return true;
				}
			}
		}
		return false;
	}
	public NguoinopthueDTO convertToDto(Nguoinopthue person) {
		NguoinopthueDTO nntDTO = mapper.map(person, NguoinopthueDTO.class);
		return nntDTO;
	}
	public Nguoinopthue convertToEntity(NguoinopthueDTO personDto) {
		Nguoinopthue nnt = mapper.map(personDto, Nguoinopthue.class);
		return nnt;
	}
	public Nguoinopthue getByMasothue(String masothue) {
		return repository.getByMaSoThue(masothue);
	}

}
