package com.gmu.grocerysplitter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberHomepageController {

	@GetMapping("/memberHomePage")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="User") String name, Model model) {
		model.addAttribute("name", name);
		return "memberHomePage";
	}

}