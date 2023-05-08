package com.sqa.controller;

import com.sqa.models.entities.SystemAdmin;
import com.sqa.services.AdminService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {

	@Autowired
	private AdminService _adminService;

	@GetMapping("")
	public String Login(Model model, @RequestParam(value = "message") Optional<String> message) {
		SystemAdmin user = new SystemAdmin();
		model.addAttribute("user", user);
		if (message.isPresent()) {
			model.addAttribute("message", message.get());// phương thức get dùng để lấy dữ liệu kiểu Optional
		}
		return "./Admin/admin_login";
	}

	@PostMapping("/processLogin")
	public String processLogin(Model model,  @ModelAttribute SystemAdmin admin, HttpServletResponse response) {
		SystemAdmin res = this._adminService.checkLogin(admin);
		if (res != null) {
			response.addCookie(new Cookie("adminId", res.getId() + ""));
			return "redirect:/admin/home";
		} else {
			model.addAttribute("message", "tên đăng nhập hoặc mật khẩu không đúng!");
			model.addAttribute("user", admin);
			return "./Admin/admin_login";
		}

	}

}
