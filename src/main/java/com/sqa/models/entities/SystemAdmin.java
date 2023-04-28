package com.sqa.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "system_admin")
public class SystemAdmin {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "hoten")
    private String hoten;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

   }
