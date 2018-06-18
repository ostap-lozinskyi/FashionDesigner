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
@RequestMapping("/readyToWear/{id}")
public class ReadyToWearIdController {
	
	private final ClothingModelService clothingModelService;

	@Autowired
	public ReadyToWearIdController(ClothingModelService clothingModelService) {
		this.clothingModelService = clothingModelService;
	}
	
	@ModelAttribute("clothingModelFilter")
	public ClothingModelFilter getFilter() {
		return new ClothingModelFilter();
	}
	
	@GetMapping
	public String show(Model model, @PathVariable Integer id, @PageableDefault Pageable pageable, 
			@ModelAttribute("clothingModelFilter") ClothingModelFilter clothingModelFilter) {
		ClothingModelView clothingModelView = clothingModelService.findClothingModelViewById(id);
		clothingModelView.setPhotoUrls(clothingModelService.findPhotoUrls(id));
		model.addAttribute("clothingModel", clothingModelView);	
		
		List <String> sectionOfClothesNames = new ArrayList<>(); 
		sectionOfClothesNames.add("Ready to wear");
		clothingModelFilter.setSectionOfClothesName(sectionOfClothesNames);
		List<ClothingModelView> clothingModelViews = clothingModelService.findAllClothingModelViews(clothingModelFilter, pageable).getContent();
		int currentListId = 0;
		for (int i = 0; i < clothingModelViews.size(); i++) {
			if (clothingModelViews.get(i).getId() == id ) {
				currentListId = i;
			}
		}		
		
		int previousId;
		if ((currentListId-1) > 0) {
			previousId = clothingModelViews.get(currentListId-1).getId();
		} else if ((currentListId-1) == 0){
			previousId = clothingModelViews.get(0).getId();
		} else {
			previousId = clothingModelViews.get(clothingModelViews.size()-1).getId();
		}
		model.addAttribute("previousModel", previousId);
		
		int nextId;
		if ((currentListId+1) < clothingModelViews.size()) {
			nextId = clothingModelViews.get(currentListId+1).getId();
		} else {
			nextId = clothingModelViews.get(0).getId();
		}
 		model.addAttribute("nextModel", nextId);
 		
 		
 		
		return "readyToWearId";
	}
	
}