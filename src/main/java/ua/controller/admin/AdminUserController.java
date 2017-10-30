package ua.controller.admin;

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

import ua.entity.Role;
import ua.model.filter.UserFilter;
import ua.model.request.FileRequest;
import ua.model.request.RegistrationRequest;
import ua.service.UserService;
import ua.validation.flag.UserFlag;

@Controller
@RequestMapping("/admin/adminUser")
@SessionAttributes("meal")
public class AdminUserController {
	
	private final UserService userService;
	
	String error = "";
	
	@Value("${file.path}")
	private String path;

	@Autowired
	public AdminUserController(UserService userService) {
		this.userService = userService;
	}

	@ModelAttribute("user")
	public RegistrationRequest getForm() {
		return new RegistrationRequest();
	}
	
	@ModelAttribute("userFilter")
	public UserFilter getFilter() {
		return new UserFilter();
	}
	
	@ModelAttribute("fileRequest")
	public FileRequest getFile() {
		return new FileRequest();
	}

	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("userFilter") UserFilter filter) {
		model.addAttribute("users", userService.findAllUsers(pageable, filter));
		model.addAttribute("error", error);
		error = "";
		if (userService.findAllUsers(pageable, filter).hasContent()||pageable.getPageNumber()==0)
			return "adminUser";
		else
			return "redirect:/admin/adminUser"+buildParams(pageable, filter);
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, @PageableDefault Pageable pageable,
			@ModelAttribute("userFilter") UserFilter filter) {
		userService.delete(id);
		return "redirect:/admin/adminUser"+buildParams(pageable, filter);
	}
	
	@GetMapping("/setDefaultPhoto/{id}")
	public String setDefaultPhoto(@PathVariable Integer id, @PageableDefault Pageable pageable,
			@ModelAttribute("userFilter") UserFilter filter) {
		userService.setDefaultPhoto(id);
		return "redirect:/admin/adminUser"+buildParams(pageable, filter);
	}
	
	@ExceptionHandler({SQLException.class,DataAccessException.class})
	public String databaseError() {
		error = "You can't delete this user because it is used!";
		return "redirect:/admin/adminUser";
	}

	@PostMapping
	public String save(@ModelAttribute("user") @Validated(UserFlag.class) RegistrationRequest request, BindingResult br,
			Model model, SessionStatus status, @PageableDefault Pageable pageable,
			@ModelAttribute("userFilter") UserFilter filter,  @ModelAttribute("fileRequest") FileRequest fileRequest) {
		if (br.hasErrors())
			return show(model, pageable, filter);
		request.setRole(Role.ROLE_ADMIN);
		userService.saveUser(request);
		return cancel(status, pageable, filter);
	}

	@GetMapping("/updateRole/{id}/{role}")
	public String updateRole(@PathVariable Integer id, @PathVariable Role role, Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("userFilter") UserFilter filter) {
		userService.updateRole(id, role);
		return show(model, pageable, filter);
	}

	@GetMapping("/cancel")
	public String cancel(SessionStatus status, @PageableDefault Pageable pageable,
			@ModelAttribute("userFilter") UserFilter filter) {
		status.setComplete();
		return "redirect:/admin/adminUser"+buildParams(pageable, filter);
	}
	
	private String buildParams(Pageable pageable, UserFilter filter) {
		StringBuilder buffer = new StringBuilder();		
		buffer.append("?page=");
		if(!(userService.findAllUsers(pageable, filter).hasContent())) 
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