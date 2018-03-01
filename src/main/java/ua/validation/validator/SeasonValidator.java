package ua.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.repository.SeasonRepository;
import ua.validation.annotation.UniqueSeason;

@Component
public class SeasonValidator implements ConstraintValidator<UniqueSeason, String> {

	private final SeasonRepository seasonRepository;

	public SeasonValidator(SeasonRepository seasonRepository) {
		this.seasonRepository = seasonRepository;
	}

	@Override
	public void initialize(UniqueSeason constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !seasonRepository.existsByName(value);
	}

}
