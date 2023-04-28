package com.sqa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sqa.models.entities.*;
@Repository
public interface NganHangHoTroThanhToanRepository extends JpaRepository<Nganhanghotrothanhtoan, String>{

}
