package ua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ua.entity.EmailCredentials;
import ua.model.filter.SimpleFilter;
import ua.service.EmailService;
import ua.validation.flag.ColorFlag;

@Controller
@RequestMapping("/admin/adminEmail")
@SessionAttributes("emailCredentials")
public class AdminEmailController {

    private final EmailService emailService;

    private String error = "";

    @Autowired
    public AdminEmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @ModelAttribute("emailCredentials")
    public EmailCredentials getForm() {
        return new EmailCredentials();
    }

    @ModelAttribute("filter")
    public SimpleFilter getFilter() {
        return new SimpleFilter();
    }

    @GetMapping
    public String showEmail(Model model, @ModelAttribute("filter") SimpleFilter filter) {
        model.addAttribute("credentials", emailService.findEmailCredentials());
        model.addAttribute("error", error);
        error = "";
        return "adminEmail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, @ModelAttribute("filter") SimpleFilter filter) {
        emailService.delete(id);
        return "redirect:/admin/adminEmail";
    }

    @PostMapping
    public String save(@ModelAttribute("emailCredentials") @Validated(ColorFlag.class) EmailCredentials email,
                       BindingResult br, Model model, SessionStatus status,
                       @ModelAttribute("filter") SimpleFilter filter) {
        if (br.hasErrors())
            return showEmail(model, filter);
        email.setId(1);
        emailService.save(email);
        return cancel(status, filter);
    }

    @GetMapping("/cancel")
    public String cancel(SessionStatus status, @ModelAttribute("filter") SimpleFilter filter) {
        status.setComplete();
        return "redirect:/admin/adminEmail";
    }
}