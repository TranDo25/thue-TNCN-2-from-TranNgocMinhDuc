package com.sqa.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sqa.models.entities.Bieuthueluytientungphan;

@Repository
public interface BieuThueLuyTienTungPhanRepository extends JpaRepository<Bieuthueluytientungphan, String> {
	@Query(value="SELECT * FROM bieuthueluytientungphan", nativeQuery=true)
	ArrayList<Bieuthueluytientungphan> getTheDefaultVersionConfig();

}
