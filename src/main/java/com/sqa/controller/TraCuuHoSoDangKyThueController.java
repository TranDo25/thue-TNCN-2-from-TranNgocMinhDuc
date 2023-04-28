package com.sqa.controller;

import java.time.LocalDate;
import java.util.List;

import com.sqa.models.entities.Hosodangkythue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sqa.models.dtos.BaoCaoTongHopSoThueDaNopModel;
import com.sqa.models.dtos.BienLaiDongTienModel;
import com.sqa.models.dtos.ChiTietDongThueModel;
import com.sqa.services.BaoCaoTongHopSoThueDaNopService;
import com.sqa.services.HoaDonDongThueService;
import com.sqa.services.TraCuuHoSoDangKyThueService;

@Controller
//@RequestMapping("/theodoi")
public class TraCuuHoSoDangKyThueController {
    @Autowired
    private TraCuuHoSoDangKyThueService traCuuHoSoDangKyThueService;
    public TraCuuHoSoDangKyThueController() {
        // TODO Auto-generated constructor stub
    }

    @GetMapping("/theodoi/nhapmasothue")
    public String nhapThongtinTracuu(Model model) {
        return "./tra_cuu/nhap_ma_so_thue";
    }
    @PostMapping("/theodoi/tracuu/hosodangkythue/action")
    public String tracuuHosoDangKyThue(@RequestParam("masothue") String masothue,
                                       @RequestParam("magiaodich") String magiaodich,
                                       Model model) {
        // Gọi phương thức traCuuHoSoDangKyThue từ service để lấy danh sách hồ sơ đăng ký thuế
        List<Hosodangkythue> listHoSo = traCuuHoSoDangKyThueService.traCuuHoSoDangKyThue(masothue, magiaodich);

        // Gửi danh sách hồ sơ và các thông tin tìm kiếm về view để hiển thị kết quả tìm kiếm
        model.addAttribute("listHoSo", listHoSo);
        model.addAttribute("maSoThue", masothue);
        model.addAttribute("maGiaodich", magiaodich);
        System.out.println(listHoSo);

        return "./tra_cuu/ket_qua_tra_cuu";
//        return "redirect:/home";
    }

}
