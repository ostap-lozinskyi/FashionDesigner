package ua.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

import ua.model.filter.CollectionFilter;
import ua.model.request.FileRequest;
import ua.model.request.CollectionRequest;
import ua.service.CollectionService;
import ua.validation.flag.CollectionFlag;

@Controller
@RequestMapping("/admin/adminCollections")
@SessionAttributes("collection")
public class AdminCollectionsController {
	
	private final CollectionService collectionService;
	
	String error = "";
	
	@Autowired
	public AdminCollectionsController(CollectionService service) {
		this.collectionService = service;
	}

	@ModelAttribute("collection")
	public CollectionRequest getForm() {
		return new CollectionRequest();
	}
	
	@ModelAttribute("collectionFilter")
	public CollectionFilter getFilter() {
		return new CollectionFilter();
	}
	
	@ModelAttribute("fileRequest")
	public FileRequest getFile() {
		return new FileRequest();
	}

	/**
	 * Show Collections page
	 */
	@GetMapping
	public String showCollections(Model model, @PageableDefault Pageable pageable, @ModelAttribute("collectionFilter") CollectionFilter filter) {
		model.addAttribute("showCollections", collectionService.findAllCollectionViews(filter, pageable));
		model.addAttribute("error", error);
		error = "";
		if (collectionService.findAllCollectionViews(filter, pageable).hasContent()||pageable.getPageNumber()==0)
			return "adminCollections";
		else
			return "redirect:/admin/adminCollections"+buildParams(pageable, filter);
	}

	/**
	 * Deleting Article
	 */
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, @PageableDefault Pageable pageable,
			@ModelAttribute("collectionFilter") CollectionFilter filter) {
		collectionService.deleteCollection(id);
		return "redirect:/admin/adminCollections"+buildParams(pageable, filter);
	}
	
	@ExceptionHandler({SQLException.class,DataAccessException.class})
	public String databaseError() {
		error = "You can't delete this collection because it is used!";
		return "redirect:/admin/adminCollections";
	}

	@PostMapping
	public String save(@ModelAttribute("collection") @Validated(CollectionFlag.class) CollectionRequest request, BindingResult br,
			Model model, SessionStatus status, @PageableDefault Pageable pageable,
			@ModelAttribute("collectionFilter") CollectionFilter filter,  @ModelAttribute("fileRequest") FileRequest fileRequest) {
		if (br.hasErrors())
			return showCollections(model, pageable, filter);
		MultipartFile multipartFile = fileRequest.getFile();
		try {
			if(!multipartFile.isEmpty()) {
				collectionService.saveCollection(collectionService.uploadPhotoToCloudinary(request, multipartFile));
			} else {
				collectionService.saveCollection(request);
			}			
		} catch (IOException e) {
			collectionService.saveCollection(request);
		}
		return cancel(status, pageable, filter);
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable Integer id, Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("collectionFilter") CollectionFilter filter) {
		model.addAttribute("collection", collectionService.findOneRequest(id));
		return showCollections(model, pageable, filter);
	}

	@GetMapping("/cancel")
	public String cancel(SessionStatus status, @PageableDefault Pageable pageable,
			@ModelAttribute("collectionFilter") CollectionFilter filter) {
		status.setComplete();
		return "redirect:/admin/adminCollections"+buildParams(pageable, filter);
	}
	
	private String buildParams(Pageable pageable, CollectionFilter filter) {
		StringBuilder buffer = new StringBuilder();		
		buffer.append("?page=");
		if(!(collectionService.findAllCollectionViews(filter, pageable).hasContent())) 
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