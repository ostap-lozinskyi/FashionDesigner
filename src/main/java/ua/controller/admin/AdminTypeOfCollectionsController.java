package ua.controller.admin;

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

import ua.entity.TypeOfCollection;
import ua.model.filter.SimpleFilter;
import ua.service.TypeOfCollectionService;
import ua.validation.flag.TypeOfCollectionFlag;

@Controller
@RequestMapping("/admin/adminTypeOfCollections")
@SessionAttributes("typeOfCollection")
public class AdminTypeOfCollectionsController {
	
	private final TypeOfCollectionService typeOfCollectionService;
	
	String error = "";
	
	@Autowired
	public AdminTypeOfCollectionsController(TypeOfCollectionService typeOfCollectionService) {
		this.typeOfCollectionService = typeOfCollectionService;
	}

	@ModelAttribute("typeOfCollection")
	public TypeOfCollection getForm() {
		return new TypeOfCollection();
	}
	
	@ModelAttribute("simpleFilter")
	public SimpleFilter getFilter() {
		return new SimpleFilter();
	}
	
	/**
	 * Show TypeOfCollection page
	 */
	@GetMapping
	public String showTypeOfCollection(Model model, @PageableDefault Pageable pageable, @ModelAttribute("simpleFilter") SimpleFilter simpleFilter) {
		model.addAttribute("showTypeOfCollections", typeOfCollectionService.findAll(pageable,simpleFilter));
		model.addAttribute("error", error);
		error = "";
		if (typeOfCollectionService.findAll(pageable,simpleFilter).hasContent()||pageable.getPageNumber()==0)
			return "adminTypeOfCollections";
		else
			return "redirect:/admin/adminTypeOfCollections"+buildParams(pageable, simpleFilter);
	}

	/**
	 * Deleting TypeOfCollection
	 */
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, @PageableDefault Pageable pageable,
			@ModelAttribute("simpleFilter") SimpleFilter simpleFilter) {
		typeOfCollectionService.delete(id);
		return "redirect:/admin/adminTypeOfCollections"+buildParams(pageable, simpleFilter);
	}
	
	@ExceptionHandler({SQLException.class,DataAccessException.class})
	public String databaseError() {
		error = "You can't delete this TypeOfcollection because it is used!";
		return "redirect:/admin/adminTypeOfCollections";
	}

	@PostMapping
	public String save(@ModelAttribute("typeOfCollection") @Validated(TypeOfCollectionFlag.class) TypeOfCollection typeOfCollection, BindingResult br,
			Model model, SessionStatus status, @PageableDefault Pageable pageable,
			@ModelAttribute("simpleFilter") SimpleFilter simpleFilter) {
		if (br.hasErrors())
			return showTypeOfCollection(model, pageable, simpleFilter);
		typeOfCollectionService.save(typeOfCollection);
		return cancel(status, pageable, simpleFilter);
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable Integer id, Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("simpleFilter") SimpleFilter simpleFilter) {
		System.out.println(id);
		model.addAttribute("typeOfCollection", typeOfCollectionService.findOne(id));
		System.out.println(model.asMap());
		return showTypeOfCollection(model, pageable, simpleFilter);
	}

	@GetMapping("/cancel")
	public String cancel(SessionStatus status, @PageableDefault Pageable pageable,
			@ModelAttribute("simpleFilter") SimpleFilter simpleFilter) {
		status.setComplete();
		return "redirect:/admin/adminTypeOfCollections"+buildParams(pageable, simpleFilter);
	}
	
	private String buildParams(Pageable pageable, SimpleFilter simpleFilter) {
		StringBuilder buffer = new StringBuilder();		
		buffer.append("?page=");
		if(!(typeOfCollectionService.findAll(pageable,simpleFilter).hasContent())) 
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
		buffer.append(simpleFilter.getSearch());
		return buffer.toString();
	}
}