package com.sqa.models.dtos;



import com.sqa.models.entities.Hoadondongthue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoadondongthueDTO {
	private String id_hoadondongthue;
	private String kyquyettoan;
	private Double sotienphaidong;
	private String id_nguoinopthue;
	private String id_coquanthue;
	private java.sql.Date ngaydongtien;
	private String id_taikhoanthanhtoan;
	private TaikhoanthanhtoanDTO tkThanhToan;
	private Double sotiendadong;

	public HoadondongthueDTO(Hoadondongthue hoaDon) {

	}

    public TaikhoanthanhtoanDTO getTaikhoanthanhtoanDTO() {
		return tkThanhToan;
	}
	public void setTaikhoanthanhtoanDTO(TaikhoanthanhtoanDTO dto) {
		this.tkThanhToan = dto;
	}

}
