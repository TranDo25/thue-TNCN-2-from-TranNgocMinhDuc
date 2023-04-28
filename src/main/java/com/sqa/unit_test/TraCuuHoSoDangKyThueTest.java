package com.sqa.unit_test;



import com.sqa.models.entities.Hosodangkythue;
import com.sqa.repositories.HoSoTraCuThueRepository;
import com.sqa.services.TraCuuHoSoDangKyThueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
public class TraCuuHoSoDangKyThueTest {
    @Mock
    private HoSoTraCuThueRepository hoSoTraCuThueRepo;

    @InjectMocks
    private TraCuuHoSoDangKyThueService traCuuHoSoDangKyThueService;

    public TraCuuHoSoDangKyThueTest() {
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testTraCuuHoSoDangKyThue() {
        // arrange
        String masothue = "1234567890";
        String magiaodich = "ABC123";
        Hosodangkythue hosodangkythue1 = new Hosodangkythue();
        hosodangkythue1.setIdHoSoDangKyThue(String.valueOf(1L));
        Hosodangkythue hosodangkythue2 = new Hosodangkythue();
        hosodangkythue2.setIdHoSoDangKyThue(String.valueOf(2L));
        List<Hosodangkythue> expectedHosodangkythues = new ArrayList<>();
        expectedHosodangkythues.add(hosodangkythue1);
        expectedHosodangkythues.add(hosodangkythue2);
        when(hoSoTraCuThueRepo.findAllByMasothueAndMagiaodich(masothue, magiaodich)).thenReturn(expectedHosodangkythues);

        // act
        List<Hosodangkythue> actualHosodangkythues = traCuuHoSoDangKyThueService.traCuuHoSoDangKyThue(masothue, magiaodich);

        // assert
        assertEquals(expectedHosodangkythues.size(), actualHosodangkythues.size());
        assertEquals(expectedHosodangkythues.get(0).getIdHoSoDangKyThue(), actualHosodangkythues.get(0).getIdHoSoDangKyThue());
        assertEquals(expectedHosodangkythues.get(1).getIdHoSoDangKyThue(), actualHosodangkythues.get(1).getIdHoSoDangKyThue());
    }
}
