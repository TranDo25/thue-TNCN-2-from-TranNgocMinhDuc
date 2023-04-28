package com.sqa.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bieuthueluytientungphan")
public class Bieuthueluytientungphan {
    @Id
    @Column(name = "id_bieuthueluytientungphan")
    private String id_bieuthueluytientungphan;

    @Column(name = "bacthue")
    private Integer bacthue;

    @Column(name = "phanthunhaptinhthuenam")
    private String phanthunhaptinhthuenam;

    @Column(name = "phanthunhaptinhthuethang")
    private String phanthunhaptinhthuethang;

    @Column(name = "thuesuat")
    private Double thuesuat;

    @Column(name = "id_cauhinh")
    private String id_cauhinh;

   }
