package com.sqa.models.dtos;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaoCaoTongHopSoThueDaNopModel {
	private NguoinopthueDTO nntDTO;
	private ArrayList<HoadondongthueDTO> dsHoaDon;

}
