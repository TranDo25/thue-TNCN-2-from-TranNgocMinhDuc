package com.sqa.controller;

import com.sqa.models.entities.Hosodangkythue;
import com.sqa.services.TraCuuHoSoDangKyThueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
//@RequestMapping("/theodoi")
public class UserTraCuuHoSoDangKyThueController {
    @Autowired
    private TraCuuHoSoDangKyThueService traCuuHoSoDangKyThueService;
    public UserTraCuuHoSoDangKyThueController() {
        // TODO Auto-generated constructor stub
    }

    @GetMapping("/theodoi/nhapmasothue")
    public String nhapThongtinTracuu(Model model) {
        return "./TraCuu/nhap_ma_so_thue";
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

        return "./TraCuu/ket_qua_tra_cuu";
//        return "redirect:/home";
    }

}
