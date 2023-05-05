package com.sqa.unit_test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sqa.models.dtos.BaoCaoTongHopSoThueDaNopModel;
import com.sqa.models.dtos.NguoinopthueDTO;
import com.sqa.services.HoaDonDongThueService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.sqa.models.dtos.HoadondongthueDTO;
import com.sqa.models.entities.Hoadondongthue;
import com.sqa.repositories.HoaDonDongThueRepository;

@RunWith(MockitoJUnitRunner.class)
public class HoaDonDongThueServiceTest {
    @Mock
    private HoaDonDongThueRepository _hddtRepo;

    @Mock
    private ModelMapper _mapper;

    @InjectMocks
    private HoaDonDongThueService service;

    @Test
    public void testGetByUserId() {
        String userId = "U001";
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 12, 31);

        java.sql.Date sql_start_date =  java.sql.Date.valueOf(startDate);
        java.sql.Date sql_end_date =  java.sql.Date.valueOf(endDate);

        List<Hoadondongthue> expectedList = new ArrayList<>();
        Hoadondongthue hddt = new Hoadondongthue();
        hddt.setId_hoadondongthue("HD001");
        hddt.setId_nguoinopthue(userId);
        expectedList.add(hddt);

        when(_hddtRepo.findAllByUserIdByRangeDate(userId, sql_start_date, sql_end_date)).thenReturn(expectedList);

        List<Hoadondongthue> result = service.getByUserId(userId, startDate, endDate);
        assertEquals(1, result.size());
        assertEquals("HD001", result.get(0).getId_hoadondongthue());
        assertEquals(userId, result.get(0).getId_nguoinopthue());
    }

    @Test
    public void testConvertToDto() {
        Hoadondongthue hddt = new Hoadondongthue();
        hddt.setId_hoadondongthue("HD001");
        hddt.setId_nguoinopthue("U001");

        HoadondongthueDTO expectedDto = new HoadondongthueDTO();
        expectedDto.setId_hoadondongthue("HD001");
        expectedDto.setId_nguoinopthue("U001");

        when(_mapper.map(hddt, HoadondongthueDTO.class)).thenReturn(expectedDto);

        HoadondongthueDTO result = service.convertToDto(hddt);
        assertEquals("HD001", result.getId_hoadondongthue());
        assertEquals("U001", result.getId_nguoinopthue());
    }

    @Test
    public void testConvertToEntity() {
        HoadondongthueDTO dto = new HoadondongthueDTO();
        dto.setId_hoadondongthue("HD002");
        dto.setId_nguoinopthue("U002");

        Hoadondongthue expectedEntity = new Hoadondongthue();
        expectedEntity.setId_hoadondongthue("HD002");
        expectedEntity.setId_nguoinopthue("U002");

        when(_mapper.map(dto, Hoadondongthue.class)).thenReturn(expectedEntity);

        Hoadondongthue result = service.convertToEntity(dto);
        assertEquals("HD002", result.getId_hoadondongthue());
        assertEquals("U002", result.getId_nguoinopthue());
    }

    @Test
    public void testCalculateTotalTaxHaveToPay() {
        List<HoadondongthueDTO> dsHoaDon = new ArrayList<>();
        HoadondongthueDTO hddt1 = new HoadondongthueDTO();
        hddt1.setSotienphaidong(10000.0);
        dsHoaDon.add(hddt1);

        HoadondongthueDTO hddt2 = new HoadondongthueDTO();
        hddt2.setSotienphaidong(20000.0);
        dsHoaDon.add(hddt2);

        BaoCaoTongHopSoThueDaNopModel res = new BaoCaoTongHopSoThueDaNopModel((NguoinopthueDTO) dsHoaDon, null);

        Double result = service.calculateTotalTaxHaveToPay(res);
        assertEquals(Double.valueOf(30000), result);
    }

    @Test
    public void testCalculateTotalTaxPaid() {
        List<HoadondongthueDTO> dsHoaDon = new ArrayList<>();
        HoadondongthueDTO hddt1 = new HoadondongthueDTO();
        hddt1.setSotiendadong(5000.0);
        dsHoaDon.add(hddt1);

        HoadondongthueDTO hddt2 = new HoadondongthueDTO();
        hddt2.setSotiendadong(10000.0);
        dsHoaDon.add(hddt2);

        BaoCaoTongHopSoThueDaNopModel res = new BaoCaoTongHopSoThueDaNopModel((NguoinopthueDTO) dsHoaDon, null);

        Double result = service.calculateTotalTaxPaid(res);
        assertEquals(Double.valueOf(15000), result);
    }

    @Test
    public void testGetHoaDonById() {
        String id = "HD001";

        Hoadondongthue hddt = new Hoadondongthue();
        hddt.setId_hoadondongthue(id);

        when(_hddtRepo.findById(id)).thenReturn(Optional.of(hddt));

        Optional<Hoadondongthue> result = service.getHoaDonById(id);
        assertEquals(id, result.get().getId_hoadondongthue());
    }
}
