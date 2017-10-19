package ua.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ua.model.request.RegistrationRequest;
import ua.validation.annotation.PasswordsEqual;

public class PasswordEqualValidator implements ConstraintValidator<PasswordsEqual, Object>{

	@Override
	public void initialize(PasswordsEqual arg0) {	}

	@Override
	public boolean isValid(Object candidate, ConstraintValidatorContext arg1) {
		RegistrationRequest user = (RegistrationRequest) candidate;
		return user.getPassword().equals(user.getRepeatPassword());
	}

}