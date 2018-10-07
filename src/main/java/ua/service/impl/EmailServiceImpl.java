package ua.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.EmailCredentials;
import ua.repository.EmailRepository;
import ua.service.EmailService;

@Service
public class EmailServiceImpl extends CrudServiceImpl<EmailCredentials, Integer> implements EmailService {

    private final EmailRepository emailRepository;

    @Autowired
    public EmailServiceImpl(EmailRepository emailRepository) {
        super(emailRepository);
        this.emailRepository = emailRepository;
    }

    @Override
    public EmailCredentials findEmailCredentials() {
        return emailRepository.findEmailCredentialsById(1);
    }
}