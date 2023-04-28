package com.sqa.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chicucthue")
public class Chicucthue {
    @Id
    @Column(name = "id_chicucthue")
    private String id_chicucthue;

    @Column(name = "ten")
    private String ten;

    @Column(name = "diachi")
    private String diachi;

    @Column(name = "email")
    private String email;

    @Column(name = "sdt")
    private String sdt;
    @Column(name = "tinhthanhpho")
    private String tinhthanhpho;
    @Column(name = "sotaikhoan")
    private String sotaikhoan;
}
