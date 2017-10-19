package ua.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.repository.PlaceRepository;
import ua.validation.annotation.UniquePlace;

@Component
public class PlaceValidator implements ConstraintValidator<UniquePlace, String> {

	private final PlaceRepository repository;

	public PlaceValidator(PlaceRepository repository) {
		this.repository = repository;
	}

	@Override
	public void initialize(UniquePlace constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			return !repository.existsByNumber(Integer.valueOf(value));
		} catch (NumberFormatException e) {
			return true;
		}
	}

}
