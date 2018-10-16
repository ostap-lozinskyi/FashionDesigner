package ua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    /**
     * Show Login page
     */
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }
}