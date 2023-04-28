package com.sqa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sqa.models.entities.Cauhinh;

@Repository
public interface CauHinhRepository extends JpaRepository<Cauhinh, String>{

}
