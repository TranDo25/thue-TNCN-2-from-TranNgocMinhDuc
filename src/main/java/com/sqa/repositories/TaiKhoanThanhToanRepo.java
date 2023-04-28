package com.sqa.repositories;

import org.springframework.stereotype.Repository;
import com.sqa.models.entities.*;

import org.springframework.data.repository.CrudRepository;
@Repository
public interface TaiKhoanThanhToanRepo extends CrudRepository<Taikhoanthanhtoan, String>{
	
	
}
