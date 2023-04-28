package com.sqa.models.dtos;

import com.sqa.models.entities.Chicucthue;
import com.sqa.models.entities.Hoadondongthue;
import com.sqa.models.entities.Nganhanghotrothanhtoan;
import com.sqa.models.entities.Nguoinopthue;
import com.sqa.models.entities.Taikhoanthanhtoan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BienLaiDongTienModel {
private Hoadondongthue HoaDonDongThue;
	private Chicucthue ChiCucThue;
	private Taikhoanthanhtoan TaiKhoanThanhToan;
	private Nganhanghotrothanhtoan NganHangHoTroThanhToan;
	private Nguoinopthue NguoiNopThue;
}

