package ua.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ua.model.filter.CollectionFilter;
import ua.service.CollectionService;

@Controller
public class CollectionsMenuController {
	
	private final CollectionService collectionService;
	
	public CollectionsMenuController(CollectionService service) {
		this.collectionService = service;
	}
	
	@ModelAttribute("collectionFilter")
	public CollectionFilter getFilter() {
		return new CollectionFilter();
	}

	/**
	 * Show collections menu page	 
	 */
	@GetMapping("/collections")
	public String collectionsMenu(Model model, @ModelAttribute("collectionFilter") CollectionFilter filter, 
			@PageableDefault Pageable pageable) {
		model.addAttribute("collections", collectionService.findAllCollectionViews(filter, pageable));
		return "collections";
	}	
	
}