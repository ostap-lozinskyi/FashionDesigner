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

import ua.model.filter.ArticleFilter;
import ua.model.request.FileRequest;
import ua.model.request.ArticleRequest;
import ua.service.CollectionService;
import ua.validation.flag.ArticleFlag;

@Controller
@RequestMapping("/admin/adminCollections")
@SessionAttributes("article")
public class AdminCollectionsController {
	
	private final CollectionService articleService;
	
	String error = "";
	
	@Autowired
	public AdminCollectionsController(CollectionService service) {
		this.articleService = service;
	}

	@ModelAttribute("article")
	public ArticleRequest getForm() {
		return new ArticleRequest();
	}
	
	@ModelAttribute("articleFilter")
	public ArticleFilter getFilter() {
		return new ArticleFilter();
	}
	
	@ModelAttribute("fileRequest")
	public FileRequest getFile() {
		return new FileRequest();
	}

	/**
	 * Show Articles page
	 */
	@GetMapping
	public String showArticles(Model model, @PageableDefault Pageable pageable, @ModelAttribute("mealFilter") ArticleFilter filter) {
		model.addAttribute("collections", articleService.findAllArticleViews(filter, pageable));
		model.addAttribute("error", error);
		error = "";
		if (articleService.findAllArticleViews(filter, pageable).hasContent()||pageable.getPageNumber()==0)
			return "adminCollections";
		else
			return "redirect:/admin/adminCollections"+buildParams(pageable, filter);
	}

	/**
	 * Deleting Article
	 */
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, @PageableDefault Pageable pageable,
			@ModelAttribute("articleFilter") ArticleFilter filter) {
		articleService.deleteMeal(id);
		return "redirect:/admin/adminCollections"+buildParams(pageable, filter);
	}
	
	@ExceptionHandler({SQLException.class,DataAccessException.class})
	public String databaseError() {
		error = "You can't delete this article because it is used!";
		return "redirect:/admin/adminCollections";
	}

	@PostMapping
	public String save(@ModelAttribute("article") @Validated(ArticleFlag.class) ArticleRequest request, BindingResult br,
			Model model, SessionStatus status, @PageableDefault Pageable pageable,
			@ModelAttribute("articleFilter") ArticleFilter filter,  @ModelAttribute("fileRequest") FileRequest fileRequest) {
		if (br.hasErrors())
			return showArticles(model, pageable, filter);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		String date = dtf.format(localDate);
		request.setDate(date);
		MultipartFile multipartFile = fileRequest.getFile();
		try {
			articleService.saveArticle(articleService.uploadPhotoToCloudinary(request, multipartFile));
		} catch (IOException e) {
			articleService.saveArticle(request);
		}
		return cancel(status, pageable, filter);
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable Integer id, Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("articleFilter") ArticleFilter filter) {
		model.addAttribute("article", articleService.findOneRequest(id));
		return showArticles(model, pageable, filter);
	}

	@GetMapping("/cancel")
	public String cancel(SessionStatus status, @PageableDefault Pageable pageable,
			@ModelAttribute("articleFilter") ArticleFilter filter) {
		status.setComplete();
		return "redirect:/admin/adminCollections"+buildParams(pageable, filter);
	}
	
	private String buildParams(Pageable pageable, ArticleFilter filter) {
		StringBuilder buffer = new StringBuilder();		
		buffer.append("?page=");
		if(!(articleService.findAllArticleViews(filter, pageable).hasContent())) 
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