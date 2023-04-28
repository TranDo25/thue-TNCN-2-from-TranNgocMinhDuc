package com.sqa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqa.models.entities.Chicucthue;
import com.sqa.repositories.ChiCucThueRepository;

@Service
public class ChiCucThueService {
@Autowired
private ChiCucThueRepository cctRepo;

public Optional<Chicucthue> getCCTById(String id_chicucthue) {
	
	return cctRepo.findById(id_chicucthue);
}


}
