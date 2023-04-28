package com.sqa.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hoadondongthue")
public class Hoadondongthue {
    @Id
    @Column(name = "id_hoadondongthue")
    private String id_hoadondongthue;

    @Column(name = "kyquyettoan")
    private String kyquyettoan;

    @Column(name = "sotienphaidong")
    private Double sotienphaidong;

    @Column(name = "id_nguoinopthue")
    private String id_nguoinopthue;

    @Column(name = "id_coquanthue")
    private String id_coquanthue;

    @Column(name = "ngaydongtien")
    private java.sql.Date ngaydongtien;

    @Column(name = "id_taikhoanthanhtoan")
    private String id_taikhoanthanhtoan;
    @Column(name="sotiendadong")
    private Double sotiendadong;

    public Hoadondongthue(Hoadondongthue hoaDon) {
    }
}
