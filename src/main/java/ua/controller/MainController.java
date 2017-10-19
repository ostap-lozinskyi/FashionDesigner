package ua.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.service.ArticleService;

@Controller
public class MainController {
	
	private final ArticleService articleService;

	@Autowired
	public MainController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@GetMapping("/")
	public String index(Model model, Principal principal) {
		if(principal!=null) {
			model.addAttribute("message", "Hello "+principal.getName());
		} else {
			model.addAttribute("message", "Hello guest");
		}
		model.addAttribute("articles", articleService.findArticlesViewsByDate());
		return "index";
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
}
