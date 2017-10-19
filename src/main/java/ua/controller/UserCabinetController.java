package ua.controller;

import java.io.File;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ua.entity.User;
import ua.model.request.FileRequest;
import ua.service.FileWriter;
import ua.service.UserService;

@Controller
public class UserCabinetController {
	
	private final FileWriter writer;
	
	private final UserService userService;
	
	@Value("${file.path}")
	private String path;
	
	public UserCabinetController(FileWriter writer, UserService userService) {
		this.writer = writer;
		this.userService = userService;
	}

	@ModelAttribute("fileRequest")
	public FileRequest getForm() {
		return new FileRequest();
	}
	
	/**
	 * Show User cabinet page
	 */
	@GetMapping("/userCabinet")
	public String userCabinet(Model model, Principal principal) {
		String email=principal.getName();
		User user = userService.findUserByEmail(email);		
		model.addAttribute("user", user);
		return "userCabinet";
	}

	/**
	 * Attaching photo to User
	 */
	@PostMapping("/userCabinet")
	public String saveFile(Model model, @ModelAttribute("fileRequest") FileRequest request,
			Principal principal) {
		String photoUrl=writer.write(request.getFile());
		File toUpload = new File(path+photoUrl);
		userService.uploadPhotoToCloudinary(toUpload, principal);
		return "redirect:/userCabinet";
	}
	
}
