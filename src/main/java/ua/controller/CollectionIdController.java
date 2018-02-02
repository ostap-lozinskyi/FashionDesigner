package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.model.view.CollectionView;
import ua.service.CollectionService;

@Controller
@RequestMapping("/collection/{id}")
public class CollectionIdController {
	
	private final CollectionService collectionService;

	String error="";

	@Autowired
	public CollectionIdController(CollectionService service) {
		this.collectionService = service;
	}
	
	/**
	 * Show Collection page
	 */
	@GetMapping
	public String show(Model model, @PathVariable Integer id) {
		CollectionView collectionView = collectionService.findCollectionViewById(id);
		model.addAttribute("collectionView", collectionView);
		model.addAttribute("tasteMeal", error);
		error="";
		return "collectionId";
	}	
	
}