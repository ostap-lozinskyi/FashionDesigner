package ua.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@Autowired
	public MainController() {
	}

	/**
	 * Show main page	 
	 */
	@GetMapping("/")
	public String showMainPage(Model model, Principal principal) {
		if(principal!=null) {
			model.addAttribute("userName", ""+principal.getName());
		}	
		return "index";
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
}
