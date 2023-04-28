package com.sqa.models.dtos;

import com.sqa.models.entities.Chicucthue;
import com.sqa.models.entities.Hoadondongthue;
import com.sqa.models.entities.Nganhanghotrothanhtoan;
import com.sqa.models.entities.Taikhoanthanhtoan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietDongThueModel {
	private Hoadondongthue HoaDonDongThue;
	private Chicucthue ChiCucThue;
	private Taikhoanthanhtoan TaiKhoanThanhToan;
	private Nganhanghotrothanhtoan NganHangHoTroThanhToan;
}
