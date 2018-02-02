package ua.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.service.CollectionService;

@Controller
public class MainController {
	
	private final CollectionService articleService;

	@Autowired
	public MainController(CollectionService articleService) {
		this.articleService = articleService;
	}

	/**
	 * Show main page	 
	 */
	@GetMapping("/")
	public String index(Model model, Principal principal) {
		if(principal!=null) {
			model.addAttribute("message", "Hello "+principal.getName());
		} else {
			return "login";
		}
		model.addAttribute("articles", articleService.findCollectionsViewsByDate());
		return "index";
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
}
