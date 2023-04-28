package com.sqa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sqa.models.entities.SystemAdmin;

@Repository
public interface AdminRepository extends JpaRepository<SystemAdmin, Integer> {
	@Query(value="select * from system_admin where system_admin.username = ?1", nativeQuery=true)
	SystemAdmin kiemTraUsername(String username); 
}
