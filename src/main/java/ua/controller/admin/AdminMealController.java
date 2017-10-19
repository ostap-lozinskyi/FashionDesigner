package ua.controller.admin;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import ua.model.filter.MealFilter;
import ua.model.request.FileRequest;
import ua.model.request.MealRequest;
import ua.service.FileWriter;
import ua.service.MealService;
import ua.validation.flag.MealFlag;

@Controller
@RequestMapping("/admin/adminMeal")
@SessionAttributes("meal")
public class AdminMealController {
	
	private final FileWriter writer;

	private final MealService service;
	
	String error = "";
	
	@Value("${file.path}")
	private String path;

	@Autowired
	public AdminMealController(FileWriter writer, MealService service) {
		this.writer = writer;
		this.service = service;
	}

	@ModelAttribute("meal")
	public MealRequest getForm() {
		return new MealRequest();
	}
	
	@ModelAttribute("mealFilter")
	public MealFilter getFilter() {
		return new MealFilter();
	}
	
	@ModelAttribute("fileRequest")
	public FileRequest getFile() {
		return new FileRequest();
	}

	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("mealFilter") MealFilter filter) {
		model.addAttribute("cuisines", service.findAllCuisinesNames());
		model.addAttribute("components", service.findAllСomponentsView());
		model.addAttribute("meals", service.findAllMealView(filter, pageable));
		model.addAttribute("error", error);
		error = "";
		if (service.findAllMealView(filter, pageable).hasContent()||pageable.getPageNumber()==0)
			return "adminMeal";
		else
			return "redirect:/admin/adminMeal"+buildParams(pageable, filter);
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, @PageableDefault Pageable pageable,
			@ModelAttribute("mealFilter") MealFilter filter) {
		service.deleteMeal(id);
		return "redirect:/admin/adminMeal"+buildParams(pageable, filter);
	}
	
	@ExceptionHandler({SQLException.class,DataAccessException.class})
	public String databaseError() {
		error = "You can't delete this meal because it is used!";
		return "redirect:/admin/adminMeal";
	}

	@PostMapping
	public String save(@ModelAttribute("meal") @Validated(MealFlag.class) MealRequest request, BindingResult br,
			Model model, SessionStatus status, @PageableDefault Pageable pageable,
			@ModelAttribute("mealFilter") MealFilter filter,  @ModelAttribute("fileRequest") FileRequest fileRequest) {
		if (br.hasErrors())
			return show(model, pageable, filter);
		String photoUrl=writer.write(fileRequest.getFile());
		File toUpload = new File(path+photoUrl);
		try {
			service.saveMeal(service.uploadPhotoToCloudinary(request, toUpload));
		} catch (IOException e) {
			service.saveMeal(request);
		}
		return cancel(status, pageable, filter);
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable Integer id, Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("mealFilter") MealFilter filter) {
		model.addAttribute("meal", service.findOneRequest(id));
		return show(model, pageable, filter);
	}

	@GetMapping("/cancel")
	public String cancel(SessionStatus status, @PageableDefault Pageable pageable,
			@ModelAttribute("mealFilter") MealFilter filter) {
		status.setComplete();
		return "redirect:/admin/adminMeal"+buildParams(pageable, filter);
	}
	
	private String buildParams(Pageable pageable, MealFilter filter) {
		StringBuilder buffer = new StringBuilder();		
		buffer.append("?page=");
		if(!(service.findAllMealIndexView(filter, pageable).hasContent())) 
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