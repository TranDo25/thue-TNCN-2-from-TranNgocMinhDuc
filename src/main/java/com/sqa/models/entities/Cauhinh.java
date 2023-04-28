package com.sqa.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cauhinh")
public class Cauhinh {
	@Id
	@Column(name = "id_cauhinh")
	private String id_cauhinh;
	@Column(name = "ngaysua")
	private Date ngaysua;
	@Column(name = "id_system_admin")
	private int id_system_admin;

}
