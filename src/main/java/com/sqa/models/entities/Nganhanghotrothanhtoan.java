package com.sqa.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "nganhanghotrothanhtoan")
public class Nganhanghotrothanhtoan {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "tennganhang")
    private String tennganhang;


}
