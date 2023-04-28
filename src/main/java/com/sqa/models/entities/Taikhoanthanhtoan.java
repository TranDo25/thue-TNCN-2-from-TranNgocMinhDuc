package com.sqa.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "taikhoanthanhtoan")
public class Taikhoanthanhtoan {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "hotenchuthe")
    private String hotenchuthe;

    @Column(name = "ngayhieuluc")
    private java.sql.Date ngayhieuluc;

    @Column(name = "sothe")
    private String sothe;

    @Column(name = "nguoinopthue_id")
    private String nguoinopthueId;

    @Column(name = "nganhanghotrothanhtoan_id")
    private String nganhanghotrothanhtoanId;


}
