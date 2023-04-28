package com.sqa.controller;

import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sqa.models.entities.Nguoinopthue;
import com.sqa.services.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String Login(Model model, @RequestParam(value = "message") Optional<String> message) {
		Nguoinopthue user = new Nguoinopthue();
		model.addAttribute("user", user);
		if (message.isPresent()) {
			model.addAttribute("message", message.get());// phương thức get dùng để lấy dữ liệu kiểu Optional
		}
		return "./HomeViews/login";
	}

	@GetMapping("/logoutProcess")
	public String logoutProcess(jakarta.servlet.http.HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("userId", null);
		cookie.setMaxAge(0);
		cookie.setSecure(true);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		response.addCookie(cookie);
		return "redirect:/";
	}

	@PostMapping("/processLogin")
	public String processLogin(Model model, Nguoinopthue user, HttpServletResponse response) {
		boolean res = this.userService.checkLogin(user);
		if (res == true) {
			response.addCookie(new Cookie("userId", user.getId() + ""));
			response.addCookie(new Cookie("tencanhan", StringUtils.replace(user.getTencanhan(), " ", "%20")));
		} else {
			model.addAttribute("message", "tên đăng nhập hoặc mật khẩu không đúng!");
			model.addAttribute("user", user);
			return "./HomeViews/login";
		}
		return "redirect:/home";
	}

}
