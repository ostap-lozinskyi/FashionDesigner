package ua.controller.admin;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import ua.model.filter.ClothingModelFilter;
import ua.model.request.FileRequest;
import ua.model.request.ClothingModelRequest;
import ua.service.ClothingModelService;
import ua.validation.flag.ClothingModelFlag;

@Controller
@RequestMapping("/admin/adminClothingModels")
@SessionAttributes("clothingModel")
public class AdminClothingModelsController {
	
	private final ClothingModelService clothingModelService;
	
	String error = "";
	
	@Autowired
	public AdminClothingModelsController(ClothingModelService clothingModelService) {
		this.clothingModelService = clothingModelService;
	}

	@ModelAttribute("clothingModel")
	public ClothingModelRequest getForm() {
		return new ClothingModelRequest();
	}
	
	@ModelAttribute("clothingModelFilter")
	public ClothingModelFilter getFilter() {
		return new ClothingModelFilter();
	}
	
	@ModelAttribute("fileRequest")
	public FileRequest getFile() {
		return new FileRequest();
	}

	/**
	 * Show Clothing Models page
	 */
	@GetMapping
	public String showClothingModel(Model model, @PageableDefault Pageable pageable, @ModelAttribute("clothingModelFilter") ClothingModelFilter clothingModelFilter) {
		model.addAttribute("showClothingModels", clothingModelService.findAllClothingModelViews(clothingModelFilter, pageable));
		model.addAttribute("collections", clothingModelService.findAllCollectionNames());
		model.addAttribute("error", error);
		error = "";
		if (clothingModelService.findAllClothingModelViews(clothingModelFilter, pageable).hasContent()||pageable.getPageNumber()==0)
			return "adminClothingModels";
		else
			return "redirect:/admin/adminClothingModels"+buildParams(pageable, clothingModelFilter);
	}

	/**
	 * Deleting ClothingModel
	 */
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, @PageableDefault Pageable pageable,
			@ModelAttribute("clothingModelFilter") ClothingModelFilter clothingModelFilter) {
		clothingModelService.deleteClothingModel(id);
		return "redirect:/admin/adminClothingModels"+buildParams(pageable, clothingModelFilter);
	}
	
	@ExceptionHandler({SQLException.class,DataAccessException.class})
	public String databaseError() {
		error = "You can't delete this clothingModel because it is used!";
		return "redirect:/admin/adminClothingModels";
	}

	@PostMapping
	public String save(@ModelAttribute("clothingModel") @Validated(ClothingModelFlag.class) ClothingModelRequest request, BindingResult br,
			Model model, SessionStatus status, @PageableDefault Pageable pageable,
			@ModelAttribute("clothingModelFilter") ClothingModelFilter filter,  @ModelAttribute("fileRequest") FileRequest fileRequest) {
		if (br.hasErrors())
			return showClothingModel(model, pageable, filter);
		MultipartFile multipartFile = fileRequest.getFile();
		try {
			if(!multipartFile.isEmpty()) {
				clothingModelService.saveClothingModel(clothingModelService.uploadPhotoToCloudinary(request, multipartFile));
			} else {
				clothingModelService.saveClothingModel(request);
			}			
		} catch (IOException e) {
			clothingModelService.saveClothingModel(request);
		}
		return cancel(status, pageable, filter);
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable Integer id, Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("clothingModelFilter") ClothingModelFilter filter) {
		model.addAttribute("clothingModel", clothingModelService.findOneRequest(id));
		return showClothingModel(model, pageable, filter);
	}

	@GetMapping("/cancel")
	public String cancel(SessionStatus status, @PageableDefault Pageable pageable,
			@ModelAttribute("clothingModelFilter") ClothingModelFilter filter) {
		status.setComplete();
		return "redirect:/admin/adminClothingModels"+buildParams(pageable, filter);
	}
	
	private String buildParams(Pageable pageable, ClothingModelFilter filter) {
		StringBuilder buffer = new StringBuilder();		
		buffer.append("?page=");
		if(!(clothingModelService.findAllClothingModelViews(filter, pageable).hasContent())) 
			buffer.append(String.valueOf(pageable.getPageNumber()));
		else {
			buffer.append(String.valueOf(pageable.getPageNumber()));
		}
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if(pageable.getSort()!=null){
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order)->{
				buffer.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC)
				buffer.append(",desc");
			});
		}
		buffer.append("&search=");
		buffer.append(filter.getSearch());
		return buffer.toString();
	}
}