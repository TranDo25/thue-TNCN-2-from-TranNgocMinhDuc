package com.sqa.controller;

import com.sqa.services.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {
	@Autowired
	private AdminService _adminService;

	@GetMapping("/home")
	public String viewHome(Model model, @CookieValue(value = "adminId", defaultValue = "-1") String stringAdminId,
			HttpServletRequest request) {
		int adminId = Integer.parseInt(stringAdminId);
		if (adminId != -1) {
			model.addAttribute("user", _adminService.getById(adminId));
			return "/Admin/admin_home";
		} else if (adminId == -1) {
			return "redirect:/admin/";
		}
		return null;

	}

}
