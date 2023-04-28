package com.sqa.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqa.models.entities.Bieuthueluytientungphan;
import com.sqa.models.entities.Bieuthuetoanphan;
import com.sqa.services.BieuThueLuyTienTungPhanService;
import com.sqa.services.BieuThueToanPhanService;
import com.sqa.services.CauHinhService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminCauHinhController {
	@Autowired
	private BieuThueLuyTienTungPhanService btltService;
	@Autowired
	private BieuThueToanPhanService _bttpService;
	@Autowired
	private CauHinhService cauHinhService;

	@GetMapping("/BieuThueLuyTienTungPhan")
	public String BieuThueLuyTienTungPhan(Model model) {
		ArrayList<Bieuthueluytientungphan> tmp = btltService.get();
		model.addAttribute("bangThueLuyTien", tmp);

		return "./CauHinh/bieu_thue_luy_tien_home";
	}

	@GetMapping("/BieuThueToanPhan")
	public String BieuThueToanPhan(Model model) {
		ArrayList<Bieuthuetoanphan> tmp = _bttpService.get();
		model.addAttribute("bieuThueToanPhan", tmp);
		return "./CauHinh/bieu_thue_toan_phan_home";
	}
//================
//	@GetMapping("/api/BieuThueLuyTienTungPhan")
//	public ResponseEntity<String> ApiBieuThueLuyTienTungPhan(Model model) {
//		ArrayList<Bieuthueluytientungphan> tmp = btltService.get();
//		ObjectMapper objectMapper = new ObjectMapper();
//		String json;
//		try {
//			json = objectMapper.writeValueAsString(tmp);
//		} catch (JsonProcessingException e) {
//			return new ResponseEntity<>("error converting object to Json", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		return ResponseEntity.ok(json);
//	}

	@GetMapping("/getBTLTById")
	public ResponseEntity<String> ApiGetBTLTById(@RequestParam("id") String id) {
		Optional<Bieuthueluytientungphan> obj = btltService.getBTLTById(id);
		ObjectMapper objectMapper = new ObjectMapper();
		String json;
		try {
			json = objectMapper.writeValueAsString(obj.get());
		} catch (JsonProcessingException e) {
			return new ResponseEntity<>("error converting object to Json", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(json);
	}

	@GetMapping("/getBTTPById")
	public ResponseEntity<String> ApiGetBTTPById(@RequestParam("id") String id) {
		Optional<Bieuthuetoanphan> obj = _bttpService.getBTTPById(id);
		ObjectMapper objectMapper = new ObjectMapper();
		String json;
		try {
			json = objectMapper.writeValueAsString(obj.get());
		} catch (JsonProcessingException e) {
			return new ResponseEntity<>("error converting object to Json", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(json);
	}
//	======================
	/*
	 * @PostMapping("/apiThemMucThueLuyTien") public ResponseEntity<Boolean>
	 * ApiThemMucThue(
	 * 
	 * @CookieValue(value = "adminId", defaultValue = "-1") String anoAdminId,
	 * 
	 * @RequestBody Bieuthueluytientungphan btlt, HttpServletResponse response,
	 * HttpServletRequest request, Model model) { Cookie[] cookies =
	 * request.getCookies(); int adminId = -1; if (cookies != null) { for (Cookie
	 * cookie : cookies) { if (cookie.getName().equals("adminId")) { String
	 * cookieValue = cookie.getValue(); adminId = Integer.parseInt(cookieValue); } }
	 * }
	 * 
	 * Cauhinh cauhinh = cauHinhService.createNewCauHinh(adminId);
	 * Bieuthueluytientungphan res = (Bieuthueluytientungphan)
	 * btltService.addNewMucThue(btlt); btlt.setId_cauhinh(cauhinh.getId_cauhinh());
	 * if (res != null) { return ResponseEntity.ok(true); } else return
	 * (ResponseEntity<Boolean>) ResponseEntity.internalServerError(); }
	 */
//	@GetMapping("/test")
//	public String testCookie(@CookieValue(value = "adminId", defaultValue = "-1") String adminId) {
//		return "helloworld";
//	}

	@PostMapping("/apiThemMucThueLuyTien")
	public ResponseEntity<Boolean> ThemMucThue(@CookieValue(value = "adminId", defaultValue = "-1") String adminIdtmp,
			@RequestBody Bieuthueluytientungphan btlt, HttpServletRequest request, Model model) {
		Bieuthueluytientungphan res = btltService.themMucThueLuyTien(adminIdtmp, btlt);
		if (res != null) {
			return ResponseEntity.ok(true);
		} else
			return (ResponseEntity<Boolean>) ResponseEntity.internalServerError();
	}
	@PostMapping("/apiThemMucThueToanPhan")
	public ResponseEntity<Boolean> ThemMucThueToanPhan(
			@CookieValue(value = "adminId", defaultValue = "-1") String adminIdtmp, @RequestBody Bieuthuetoanphan bttp,
			HttpServletRequest request, Model model) {
		
		Bieuthuetoanphan res = _bttpService.themMucThueToanPhan(adminIdtmp,bttp);
		if (res != null) {
			return ResponseEntity.ok(true);
		} else
			return (ResponseEntity<Boolean>) ResponseEntity.internalServerError();
	}

//===================
	@PostMapping("/apiUpdateMucThueLuyTien")
	public ResponseEntity<Boolean> UpdateMucThue(@CookieValue(value = "adminId", defaultValue = "-1") String adminIdtmp,
			@RequestBody Bieuthueluytientungphan btlt, HttpServletRequest request, Model model) {
		int adminId = Integer.parseInt(adminIdtmp);
		Optional<Bieuthueluytientungphan> btlt2 = btltService.getBTLTById(btlt.getId_bieuthueluytientungphan());
		if (btlt2.isPresent()) {
			Bieuthueluytientungphan btlt3 = btlt2.get();
			btlt3.setBacthue(btlt.getBacthue());
			btlt3.setPhanthunhaptinhthuenam(btlt.getPhanthunhaptinhthuenam());
			btlt3.setPhanthunhaptinhthuethang(btlt.getPhanthunhaptinhthuethang());
			btlt3.setThuesuat(btlt.getThuesuat());
			Bieuthueluytientungphan res = btltService.save(btlt3);
			if (res != null) {
				return ResponseEntity.ok(true);
			}
		}
		return (ResponseEntity<Boolean>) ResponseEntity.internalServerError();

	}

	@PostMapping("/apiUpdateMucThueToanPhan")
	public ResponseEntity<Boolean> UpdateMucThueToanPhan(
			@CookieValue(value = "adminId", defaultValue = "-1") String adminIdtmp, @RequestBody Bieuthuetoanphan bttp,
			HttpServletRequest request, Model model) {
		int adminId = Integer.parseInt(adminIdtmp);
		Optional<Bieuthuetoanphan> bttp2 = _bttpService.getBTTPById(bttp.getId_bieuthuetoanphan());
		if (bttp2.isPresent()) {
			Bieuthuetoanphan bttp3 = bttp2.get();
			bttp3.setThuesuat(bttp.getThuesuat());
			bttp3.setThunhaptinhthue(bttp.getThunhaptinhthue());
			Bieuthuetoanphan res = _bttpService.save(bttp3);
			if (res != null) {
				return ResponseEntity.ok(true);
			}
		}
		return (ResponseEntity<Boolean>) ResponseEntity.internalServerError();
	}

	@GetMapping("/apiXoaMocThueLuyTien/{id_muc_thue_se_xoa}")
	public ResponseEntity<Boolean> DeleteMucThue(@PathVariable("id_muc_thue_se_xoa") String idMucThue,
			@CookieValue(value = "adminId", defaultValue = "-1") int adminId, Model model) {
		boolean res = btltService.deleteById(idMucThue);
		if (res == true)
			return ResponseEntity.ok(true);
		else
			return (ResponseEntity<Boolean>) ResponseEntity.internalServerError();
	}

	@GetMapping("/apiXoaMocThueToanPhan/{id_muc_thue_se_xoa}")
	public ResponseEntity<Boolean> ApiXoaMucThueToanPhan(@PathVariable("id_muc_thue_se_xoa") String idMucThue,
			@CookieValue(value = "adminId", defaultValue = "-1") int adminId, Model model) {
		boolean res = _bttpService.deleteById(idMucThue);
		if (res == true)
			return ResponseEntity.ok(true);
		else
			return (ResponseEntity<Boolean>) ResponseEntity.internalServerError();
	}
}
