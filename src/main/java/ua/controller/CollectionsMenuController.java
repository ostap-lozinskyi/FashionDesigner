package ua.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ua.model.filter.ArticleFilter;
import ua.service.CollectionService;

@Controller
public class CollectionsMenuController {
	
	private final CollectionService articleService;
	
	public CollectionsMenuController(CollectionService service) {
		this.articleService = service;
	}
	
	@ModelAttribute("articleFilter")
	public ArticleFilter getFilter() {
		return new ArticleFilter();
	}

	/**
	 * Show collections menu page	 
	 */
	@GetMapping("/collections")
	public String collectionsMenu(Model model, @ModelAttribute("articleFilter") ArticleFilter filter, 
			@PageableDefault Pageable pageable) {
		model.addAttribute("collections", articleService.findAllArticleViews(filter, pageable));
		return "collections";
	}	
	
}