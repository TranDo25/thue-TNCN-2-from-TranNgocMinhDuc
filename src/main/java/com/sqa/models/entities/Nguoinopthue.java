package com.sqa.models.entities;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "nguoinopthue")
public class Nguoinopthue {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "sogiayto")
    private String sogiayto;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "create_time")
    private java.sql.Timestamp createTime;

    @Column(name = "masothue")
    private String masothue;

    @Column(name = "tencanhan")
    private String tencanhan;

    @Column(name = "tinh/TP")
    private String tinhTp;

    @Column(name = "coquancapMST")
    private String coquancapMst;

    @Column(name = "loaigiayto")
    private String loaigiayto;

    @Column(name = "sodienthoai")
    private String sodienthoai;

    @Column(name = "gioitinh")
    private Boolean gioitinh;
    @Column(name = "diachilienlac")
    private String diachilienlac;


    public Nguoinopthue(String id, String username, String password, String tencanhan) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.tencanhan = tencanhan;
    }

    public Nguoinopthue() {

    }

    public Nguoinopthue(String id,  String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
