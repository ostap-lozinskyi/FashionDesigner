package ua.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.repository.ColorRepository;
import ua.validation.annotation.UniqueColor;

@Component
public class ColorValidator implements ConstraintValidator<UniqueColor, String> {

	private final ColorRepository colorRepository;

	public ColorValidator(ColorRepository colorRepository) {
		this.colorRepository = colorRepository;
	}

	@Override
	public void initialize(UniqueColor constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !colorRepository.existsByName(value);
	}

}
