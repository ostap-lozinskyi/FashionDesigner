package ua.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.repository.TypeOfCollectionRepository;
import ua.validation.annotation.UniqueTypeOfCollection;

@Component
public class TypeOfCollectionValidator implements ConstraintValidator<UniqueTypeOfCollection, String> {

	private final TypeOfCollectionRepository repository;

	public TypeOfCollectionValidator(TypeOfCollectionRepository repository) {
		this.repository = repository;
	}

	@Override
	public void initialize(UniqueTypeOfCollection constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !repository.existsByName(value);
	}

}
