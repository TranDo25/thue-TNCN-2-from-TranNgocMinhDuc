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
@Table(name = "bieuthuetoanphan")
public class Bieuthuetoanphan {
    @Id
    @Column(name = "id_bieuthuetoanphan")
    private String id_bieuthuetoanphan;

    @Column(name = "thunhaptinhthue")
    private String thunhaptinhthue;

    @Column(name = "thuesuat")
    private Double thuesuat;

    @Column(name = "id_cauhinh")
    private String id_cauhinh;

   }
