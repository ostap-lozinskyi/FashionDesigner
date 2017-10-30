package ua.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import ua.entity.Role;
import ua.model.request.RegistrationRequest;
import ua.service.UserService;
import ua.validation.flag.UserFlag;

@Controller
public class RegistrationAndLoginController {
	
	private final UserService userService;
	
	@Autowired
	public RegistrationAndLoginController(UserService userService, AuthenticationManager authenticationManager,
			HttpServletRequest httpServletRequest) {
		this.userService = userService;
	}

	/**
	 * Show Registration page	 
	 */
	@GetMapping("/registration")
	public String showRegistration(Model model) {
		model.addAttribute("registration", new RegistrationRequest());
		return "registration";
	}
	
	/**
	 * Registering new User	 
	 */
	@PostMapping("/registration")
	public String save(@ModelAttribute("registration") @Validated(UserFlag.class) RegistrationRequest request,
			BindingResult br, Model model, SessionStatus status) {
		if (br.hasErrors())
			return "registration";
		request.setRole(Role.ROLE_CLIENT);
		userService.saveUser(request);
		return "redirect:/login";
	}
	
	/**
	 * Show Login page	 
	 */
	@GetMapping("/login")
	public String showLogin(Principal principal) {
		return "login";
	}	
	
}
