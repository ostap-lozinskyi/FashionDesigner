package ua.controller.admin;

import java.security.Principal;
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

import ua.model.filter.PlaceFilter;
import ua.model.request.PlaceRequest;
import ua.service.PlaceService;
import ua.validation.flag.PlaceFlag;

@Controller
@RequestMapping("/admin/adminPlace")
@SessionAttributes("place")
public class AdminPlaceController {

	private final PlaceService service;
	
	String error = "";

	@Autowired
	public AdminPlaceController(PlaceService service) {
		this.service = service;
	}

	@ModelAttribute("place")
	public PlaceRequest getForm() {
		return new PlaceRequest();
	}
	
	@ModelAttribute("placeFilter")
	public PlaceFilter getFilter() {
		return new PlaceFilter();
	}

	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("placeFilter") PlaceFilter filter) {
		model.addAttribute("places", service.findAllView(pageable, filter));
		model.addAttribute("placesString", service.findAllPlacesCountOfPeople());
		model.addAttribute("error", error);
		error = "";
		if (service.findAllView(pageable, filter).hasContent()||pageable.getPageNumber()==0)
			return "adminPlace";
		else
			return "redirect:/admin/adminPlace"+buildParams(pageable, filter);
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, @PageableDefault Pageable pageable,
			@ModelAttribute("placeFilter") PlaceFilter filter) {
		service.deletePlace(id);
		return "redirect:/admin/adminPlace"+buildParams(pageable, filter);
	}
	
	@ExceptionHandler({SQLException.class,DataAccessException.class})
	public String databaseError() {
		error = "You can't delete this place because it is used!";
		return "redirect:/admin/adminPlace";
	}

	@PostMapping
	public String save(@ModelAttribute("place") @Validated(PlaceFlag.class) PlaceRequest request, BindingResult br,
			Model model, SessionStatus status, @PageableDefault Pageable pageable,
			@ModelAttribute("placeFilter") PlaceFilter filter) {
		if (br.hasErrors())
			return show(model, pageable, filter);
		service.savePlace(request);
		return cancel(status, pageable, filter);
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable Integer id, Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("placeFilter") PlaceFilter filter) {
		model.addAttribute("place", service.findOneRequest(id));
		return show(model, pageable, filter);
	}

	@GetMapping("/cancel")
	public String cancel(SessionStatus status, @PageableDefault Pageable pageable,
			@ModelAttribute("placeFilter") PlaceFilter filter) {
		status.setComplete();
		return "redirect:/admin/adminPlace"+buildParams(pageable, filter);
	}
	
	@GetMapping("/setUserAdmin/{id}")
	public String setUserAdmin(@PathVariable Integer id, Principal principal) {
		service.updatePlaceUserId(id, principal);
		return "redirect:/admin/adminPlace";
	}
	
	private String buildParams(Pageable pageable, @ModelAttribute("placeFilter") PlaceFilter filter) {
		StringBuilder buffer = new StringBuilder();		
		buffer.append("?page=");
		if(!(service.findAllView(pageable, filter).hasContent())) 
			buffer.append(String.valueOf(pageable.getPageNumber()));
		else {
			buffer.append(String.valueOf(pageable.getPageNumber()+1));
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
		return buffer.toString();
	}
	
}