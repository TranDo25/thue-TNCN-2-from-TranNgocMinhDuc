package com.sqa.repositories;

import com.sqa.models.entities.Hosodangkythue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//public interface HoSoTraCuThueRepository {
//
//}

@Repository
public interface HoSoTraCuThueRepository extends JpaRepository<Hosodangkythue, String> {
    @Query(value = "Select e from Hosodangkythue e where e.magiaodich = :magiaodich and e.nguoinopthueId = :masothue")
    List<Hosodangkythue> findAllByMasothueAndMagiaodich(@Param("masothue") String masothue, @Param("magiaodich") String magiaodich);
}
