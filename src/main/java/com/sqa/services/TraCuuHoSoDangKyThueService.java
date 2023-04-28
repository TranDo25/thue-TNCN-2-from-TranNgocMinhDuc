package com.sqa.services;

import com.sqa.models.entities.Hosodangkythue;
import com.sqa.repositories.HoSoTraCuThueRepository;
import com.sqa.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraCuuHoSoDangKyThueService {
    private HoSoTraCuThueRepository hoSoTraCuThueRepo;
    @Autowired
    private UserRepository repository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    public void TraCuuHoSoDangKyThueService(HoSoTraCuThueRepository hoSoTraCuThueRepo) {
        this.hoSoTraCuThueRepo = hoSoTraCuThueRepo;
    }
    private String masothue;
    private String magiaodich;
    //    private String phone;
    public String getMasothue() {
        return masothue;
    }
    public void setMasothue(String masothue) {
        this.masothue = masothue;
    }
    public String getMagiaodich() {
        return magiaodich;
    }
    public void setMagiaodich(String magiaodich) {
        this.magiaodich = magiaodich;
    }
    public List<Hosodangkythue> traCuuHoSoDangKyThue(String masothue, String magiaodich) {
        return (List<Hosodangkythue>) hoSoTraCuThueRepo.findAllByMasothueAndMagiaodich(masothue, magiaodich);
    }


}

