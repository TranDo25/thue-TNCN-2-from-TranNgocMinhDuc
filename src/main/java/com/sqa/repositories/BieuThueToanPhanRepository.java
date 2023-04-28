package com.sqa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sqa.models.entities.Bieuthuetoanphan;

@Repository
public interface BieuThueToanPhanRepository extends JpaRepository<Bieuthuetoanphan,String>{

}
