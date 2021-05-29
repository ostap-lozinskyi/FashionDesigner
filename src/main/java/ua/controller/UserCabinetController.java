package ua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import ua.entity.User;
import ua.model.request.FileRequest;
import ua.service.UserService;

import java.security.Principal;

@Controller
public class UserCabinetController {

    private final UserService userService;

    public UserCabinetController(UserService userService) {
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
        String email = principal.getName();
        User user = userService.findUserByEmail(email);
        model.addAttribute("user", user);
        return "userCabinet";
    }

    /**
     * Attaching photo to User
     */
    @PostMapping("/userCabinet")
    public String saveFile(@ModelAttribute("fileRequest") FileRequest fileRequest, Principal principal) {
        MultipartFile multipartFile = fileRequest.getFile();
        userService.uploadPhotoToCloudinary(multipartFile, principal);
        return "redirect:/userCabinet";
    }
}