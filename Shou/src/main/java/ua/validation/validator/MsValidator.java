package ua.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.repository.MsRepository;
import ua.validation.annotation.UniqueMs;

@Component
public class MsValidator implements ConstraintValidator<UniqueMs, String> {

	private final MsRepository repository;

	public MsValidator(MsRepository repository) {
		this.repository = repository;
	}

	@Override
	public void initialize(UniqueMs constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !repository.existsByName(value);
	}

}
