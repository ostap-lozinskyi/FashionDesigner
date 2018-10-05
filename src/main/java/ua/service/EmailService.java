package ua.service;

import ua.entity.EmailCredentials;

public interface EmailService extends CrudService<EmailCredentials, Integer>{

	EmailCredentials findEmailCredentials();
}