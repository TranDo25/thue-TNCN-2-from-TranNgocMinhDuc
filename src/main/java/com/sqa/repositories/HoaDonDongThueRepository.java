package com.sqa.repositories;

import org.springframework.stereotype.Repository;
import com.sqa.models.entities.*;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
@Repository
public interface HoaDonDongThueRepository extends JpaRepository<Hoadondongthue,String> {
	@Query(value="Select * from hoadondongthue where hoadondongthue.id_nguoinopthue = ?1 and hoadondongthue.ngaydongtien >= ?2 and hoadondongthue.ngaydongtien <= ?3",nativeQuery=true)
	public List<Hoadondongthue> findAllByUserIdByRangeDate(String userId, Date startDate, Date endDate);
}
