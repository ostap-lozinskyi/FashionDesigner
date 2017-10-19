package ua.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ua.model.filter.ArticleFilter;
import ua.service.ArticleService;

@Controller
public class ArticlesMenuController {
	
	private final ArticleService articleService;
	
	public ArticlesMenuController(ArticleService service) {
		this.articleService = service;
	}
	
	@ModelAttribute("articleFilter")
	public ArticleFilter getFilter() {
		return new ArticleFilter();
	}

	@GetMapping("/articlesMenu")
	public String mealMenu(Model model, @ModelAttribute("articleFilter") ArticleFilter filter, 
			@PageableDefault Pageable pageable) {
		model.addAttribute("articles", articleService.findAllArticleViews(filter, pageable));
		return "articlesMenu";
	}	
	
}