package ua.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import ua.entity.Role;
import ua.model.request.RegistrationRequest;
import ua.service.UserService;
import ua.service.FacebookService;
import ua.service.GoogleService;
import ua.validation.flag.UserFlag;

@Controller
public class RegistrationAndLoginController {
	
	private final UserService userService;
	
	private final FacebookService facebookService;
	
	private final GoogleService googleService;
	
	private final AuthenticationManager authenticationManager;

	private final HttpServletRequest httpServletRequest;

	@Autowired
	public RegistrationAndLoginController(UserService userService, FacebookService facebookService,
			GoogleService googleService, AuthenticationManager authenticationManager,
			HttpServletRequest httpServletRequest) {
		this.userService = userService;
		this.facebookService = facebookService;
		this.googleService = googleService;
		this.authenticationManager = authenticationManager;
		this.httpServletRequest = httpServletRequest;
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
	
	/**
	 * Redirecting to Facebook	 
	 */
	@GetMapping("/createFacebookAuthorization")
	public String createFacebookAuthorization(){
		String string = facebookService.createFacebookAuthorizationURL();
        return "redirect:"+string;
    }
	
	/**
	 * Getting User credentials from Facebook 
	 */
	@GetMapping("/facebook")
	public String getFacebookUserCredentials(@RequestParam("code") String code){
		List<String> credentials = facebookService.createFacebookAccessToken(code);
	    authenticateUser(credentials.get(0), credentials.get(1), httpServletRequest);
	    return "redirect:/";
	}	
	
	/**
	 * Redirecting to Google	 
	 */
	@GetMapping("/createGoogleAuthorization")
	public String createGoogleAuthorization(){
		String string = googleService.createGoogleAuthorizationURL();
        return "redirect:"+string;
    }
	
	/**
	 * Getting User credentials from Google 
	 */
	@GetMapping("/google")
	public String getGoogleUserCredentials(@RequestParam("code") String code){
		List<String> credentials = googleService.createGoogleAccessToken(code);
	    authenticateUser(credentials.get(0), credentials.get(1), httpServletRequest);
	    return "redirect:/";
	}	
	
	/**
	 * Automatic login without login form.	 
	 */
	private void authenticateUser(String username, String password, HttpServletRequest request) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				username, password);
		authToken.setDetails(new WebAuthenticationDetails(request));
		Authentication authentication = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
}
