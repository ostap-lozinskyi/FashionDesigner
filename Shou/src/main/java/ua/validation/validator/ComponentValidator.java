package ua.validation.validator;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.model.request.ComponentRequest;
import ua.repository.ComponentRepository;
import ua.validation.annotation.UniqueComponent;

@Component
public class ComponentValidator implements ConstraintValidator<UniqueComponent, ComponentRequest> {

	private final ComponentRepository repository;

	public ComponentValidator(ComponentRepository repository) {
		this.repository = repository;
	}

	@Override
	public void initialize(UniqueComponent constraintAnnotation) {
	}

	@Override
	public boolean isValid(ComponentRequest request, ConstraintValidatorContext context) {
		return repository.existsComponent(request.getIngredient(), new BigDecimal(request.getAmount()), request.getMs())==null;
	}

}
