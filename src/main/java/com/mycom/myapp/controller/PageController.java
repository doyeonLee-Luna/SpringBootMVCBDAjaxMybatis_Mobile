package com.mycom.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// jsp 페에지 이동
@Controller
public class PageController {
	@GetMapping("/customer/")
	public String customer() {
		return "customers";
	}
	
	@GetMapping("/mobile/")
	public String mobile() {
		return "mobiles";
	}
	
	@GetMapping("/sale/")
	public String sale() {
		return "sales";
	}
}
