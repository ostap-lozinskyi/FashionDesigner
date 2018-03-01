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

import ua.entity.Season;
import ua.model.filter.SimpleFilter;
import ua.service.SeasonService;
import ua.validation.flag.SeasonFlag;

@Controller
@RequestMapping("/admin/adminSeasons")
@SessionAttributes("season")
public class AdminSeasonsController {
	
	private final SeasonService seasonService;
	
	String error = "";
	
	@Autowired
	public AdminSeasonsController(SeasonService seasonService) {
		this.seasonService = seasonService;
	}

	@ModelAttribute("season")
	public Season getForm() {
		return new Season();
	}
	
	@ModelAttribute("filter")
	public SimpleFilter getFilter() {
		return new SimpleFilter();
	}
	
	/**
	 * Show Seasons page
	 */
	@GetMapping
	public String showSeasons(Model model, @PageableDefault Pageable pageable, 
			@ModelAttribute("filter") SimpleFilter filter) {
		model.addAttribute("showSeasons", seasonService.findAll(pageable, filter));
		model.addAttribute("error", error);
		error = "";
		if (seasonService.findAll(pageable,filter).hasContent()||pageable.getPageNumber()==0)
			return "adminSeasons";
		else
			return "redirect:/admin/adminSeasons"+buildParams(pageable, filter);
	}

	/**
	 * Deleting Season
	 */
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") SimpleFilter filter) {
		seasonService.delete(id);
		return "redirect:/admin/adminSeasons"+buildParams(pageable, filter);
	}
	
	@ExceptionHandler({SQLException.class,DataAccessException.class})
	public String databaseError() {
		error = "You can't delete this season because it is used!";
		return "redirect:/admin/adminSeasons";
	}

	@PostMapping
	public String save(@ModelAttribute("season") @Validated(SeasonFlag.class) Season season, BindingResult br,
			Model model, SessionStatus status, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") SimpleFilter filter) {
		if (br.hasErrors())
			return showSeasons(model, pageable, filter);
		seasonService.save(season);
		return cancel(status, pageable, filter);
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable Integer id, Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") SimpleFilter filter) {
		model.addAttribute("season", seasonService.findOne(id));
		return showSeasons(model, pageable, filter);
	}

	@GetMapping("/cancel")
	public String cancel(SessionStatus status, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") SimpleFilter filter) {
		status.setComplete();
		return "redirect:/admin/adminSeasons"+buildParams(pageable, filter);
	}
	
	private String buildParams(Pageable pageable, SimpleFilter filter) {
		StringBuilder buffer = new StringBuilder();		
		buffer.append("?page=");
		if(!(seasonService.findAll(pageable, filter).hasContent())) 
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