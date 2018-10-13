package ua.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ua.model.filter.ClothingModelFilter;
import ua.model.view.ClothingModelView;
import ua.service.ClothingModelService;

@Controller
public class MainController {

    private final ClothingModelService clothingModelService;

    @Autowired
    public MainController(ClothingModelService clothingModelService) {
        this.clothingModelService = clothingModelService;
    }

    /**
     * Show main page
     */
    @GetMapping("/")
    public String showMainPage(Model model, Principal principal, @PageableDefault Pageable pageable,
                               @ModelAttribute("clothingModelFilter") ClothingModelFilter clothingModelFilter) {
        // Show only 'Haute Couture' clothes
        List<String> sectionOfClothesNames = new ArrayList<>();
        sectionOfClothesNames.add("Haute Couture");
        clothingModelFilter.setSectionOfClothesName(sectionOfClothesNames);

        Page<ClothingModelView> clothingModelViewsPage = clothingModelService.findAllClothingModelViews(
                clothingModelFilter, pageable);
        List<ClothingModelView> clothingModelViewsList = clothingModelViewsPage.getContent();

        int size = clothingModelViewsList.size();
        List<ClothingModelView> lastClothingModelViewsList = new ArrayList<>();
        if (size > 3) {
            lastClothingModelViewsList.add(clothingModelViewsList.get(size - 1));
            lastClothingModelViewsList.add(clothingModelViewsList.get(size - 2));
            lastClothingModelViewsList.add(clothingModelViewsList.get(size - 3));
        }

        model.addAttribute("showClothingModels", lastClothingModelViewsList);
        for (ClothingModelView clothingModelView : lastClothingModelViewsList) {
            clothingModelView.setPhotoUrls(clothingModelService.findPhotoUrls(clothingModelView.getId()));
        }
        return "index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/aboutUs")
    public String aboutUs() {
        return "aboutUs";
    }

    /**
     * Handling exceptions
     */
    @GetMapping({"/*", "/*/*"})
    public String handle() {
        return "index";
    }

}