package ua.controller;


import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.model.request.EmailRequest;
import ua.service.EmailService;
import ua.validation.flag.EmailFlag;

@Controller
@RequestMapping("/contacts")
public class ContactsController {

    private final EmailService emailService;

    private String emailSent;

    @Autowired
    public ContactsController(EmailService emailService) {
        this.emailService = emailService;
    }

    @ModelAttribute("email")
    public EmailRequest getForm() {
        return new EmailRequest();
    }

    @GetMapping
    public String showContacts(Model model) {
        model.addAttribute("emailSent", emailSent);
        emailSent = "";
        return "contacts";
    }

    @PostMapping
    public String save(@ModelAttribute("email") @Validated(EmailFlag.class) EmailRequest emailRequest,
                       BindingResult br, Model model) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        mailSender.setUsername(emailService.findEmailCredentials().getSenderMail());
        mailSender.setPassword(emailService.findEmailCredentials().getSenderPassword());
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(emailService.findEmailCredentials().getReceiverMail());
            helper.setText("повідомлення:\n" + emailRequest.getText() + "\nконтакти:\n" + emailRequest.getTel());
            helper.setSubject("Повідомлення з сайту");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        if (br.hasErrors())
            return showContacts(model);

        mailSender.send(message);
        emailSent = "Ваш лист відправлено. Гарного дня)";
        return "redirect:/contacts";
    }
}
