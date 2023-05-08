package com.sqa.repositories;

import com.sqa.models.entities.Hosodangkythue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//public interface HoSoTraCuThueRepository {
//
//}

@Repository
public interface HoSoTraCuThueRepository extends JpaRepository<Hosodangkythue, String> {
    @Query(value = "select " +
            "hosodangkythue.* " +
            "from hosodangkythue, nguoinopthue \n" +
            "where hosodangkythue.magiaodich = 'mgdhsdkt001'\n" +
            " and hosodangkythue.nguoinopthue_id = nguoinopthue.id\n" +
            " and nguoinopthue.masothue = '8675380098'", nativeQuery = true)
    List<Hosodangkythue> findAllByMasothueAndMagiaodich(String magiaodich, String masothue);
}
