package ua.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.model.filter.ClothingModelFilter;
import ua.model.view.ClothingModelView;
import ua.service.ClothingModelService;

@Controller
@RequestMapping("/clothingModel/{id}")
public class ClothingModelIdController {
	
	private final ClothingModelService clothingModelService;

	@Autowired
	public ClothingModelIdController(ClothingModelService clothingModelService) {
		this.clothingModelService = clothingModelService;
	}
	
	@ModelAttribute("clothingModelFilter")
	public ClothingModelFilter getFilter() {
		return new ClothingModelFilter();
	}
	
	@GetMapping
	public String show(Model model, @PathVariable Integer id, @PageableDefault Pageable pageable, 
			@ModelAttribute("clothingModelFilter") ClothingModelFilter clothingModelFilter) {
		List<ClothingModelView> clothingModelViews = clothingModelService.findAllClothingModelViews(clothingModelFilter, pageable).getContent();
		List<Integer> iDs = new ArrayList<>();
		int currentListId = 0;
		for (int i = 0; i < clothingModelViews.size(); i++) {
			int clothingModelViewID = clothingModelViews.get(i).getId();			
			if (clothingModelViewID == id ) {
				currentListId = i;
			}
			iDs.add(clothingModelViewID);
		}
		
		model.addAttribute("clothingModel", clothingModelService.findClothingModelViewById(id));
		int previousId = 0;
		if ((currentListId-1) > 0) {
			previousId = iDs.get(currentListId-1);
			System.out.println(currentListId-1);
		} else if ((currentListId-1) == 0){
			previousId = iDs.get(0);
		} else {
			previousId = iDs.get(iDs.size()-1);
		}
		model.addAttribute("previousModel", previousId);
		
		int nextId = 0;
		if ((currentListId+1) < iDs.size()) {
			nextId = iDs.get(currentListId+1);
		} else {
			nextId = iDs.get(0);
		}
 		model.addAttribute("nextModel", nextId);
 		
		return "clothingModelId";
	}
	
}