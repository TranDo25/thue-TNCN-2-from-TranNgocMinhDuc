package com.sqa.repositories;

import org.springframework.stereotype.Repository;
import com.sqa.models.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface ChiCucThueRepository extends JpaRepository<Chicucthue, String> {
	

}
