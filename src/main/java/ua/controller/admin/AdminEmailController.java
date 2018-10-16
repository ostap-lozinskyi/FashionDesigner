package ua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
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

    /**
     * Show Colors page
     */
    @GetMapping
    public String showEmail(Model model, @PageableDefault Pageable pageable,
                            @ModelAttribute("filter") SimpleFilter filter) {
        model.addAttribute("credentials", emailService.findEmailCredentials());
        model.addAttribute("error", error);
        error = "";
        return "adminEmail";
    }

    /**
     * Deleting Color
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, @PageableDefault Pageable pageable,
                         @ModelAttribute("filter") SimpleFilter filter) {
        emailService.delete(id);
        return "redirect:/admin/adminEmail";
    }

//	@ExceptionHandler({ SQLException.class, DataAccessException.class })
//	public String databaseError() {
//		error = "You can't delete this color because it is used!";
//		return "redirect:/admin/adminColors";
//	}

    @PostMapping
    public String save(@ModelAttribute("emailCredentials") @Validated(ColorFlag.class) EmailCredentials email, BindingResult br,
                       Model model, SessionStatus status, @PageableDefault Pageable pageable,
                       @ModelAttribute("filter") SimpleFilter filter) {
        if (br.hasErrors())
            return showEmail(model, pageable, filter);
        email.setId(1);
        emailService.save(email);
        return cancel(status, pageable, filter);
    }

//	@GetMapping("/update/{id}")
//	public String update(@PathVariable Integer id, Model model, @PageableDefault Pageable pageable,
//			@ModelAttribute("filter") SimpleFilter filter) {
//		model.addAttribute("color", email.findOne(id));
//		return showColors(model, pageable, filter);
//	}

    @GetMapping("/cancel")
    public String cancel(SessionStatus status, @PageableDefault Pageable pageable,
                         @ModelAttribute("filter") SimpleFilter filter) {
        status.setComplete();
        return "redirect:/admin/adminEmail";
    }
}