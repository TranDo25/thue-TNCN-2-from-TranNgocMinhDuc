package com.sqa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sqa.models.entities.Nguoinopthue;
import com.sqa.models.entities.SystemAdmin;
import com.sqa.services.AdminService;
import com.sqa.services.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService _adminService;

	@GetMapping("")
	public String Login(Model model, @RequestParam(value = "message") Optional<String> message) {
		SystemAdmin user = new SystemAdmin();
		model.addAttribute("user", user);
		if (message.isPresent()) {
			model.addAttribute("message", message.get());// phương thức get dùng để lấy dữ liệu kiểu Optional
		}
		return "./admin/admin_login";
	}

	@PostMapping("/processLogin")
	public String processLogin(Model model, SystemAdmin admin, HttpServletResponse response) {
		SystemAdmin res = this._adminService.checkLogin(admin);
		if (res != null) {
			response.addCookie(new Cookie("adminId", res.getId() + ""));
			return "redirect:/admin/home";
		} else {
			model.addAttribute("message", "tên đăng nhập hoặc mật khẩu không đúng!");
			model.addAttribute("user", admin);
			return "./admin/admin_login";
		}

	}

}
