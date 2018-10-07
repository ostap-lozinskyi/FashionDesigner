package ua.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.repository.ClothingModelRepository;
import ua.validation.annotation.UniqueClothingModel;

@Component
public class ClothingModelValidator implements ConstraintValidator<UniqueClothingModel, String> {

    private final ClothingModelRepository clothingModelRepository;

    public ClothingModelValidator(ClothingModelRepository clothingModelRepository) {
        this.clothingModelRepository = clothingModelRepository;
    }

    @Override
    public void initialize(UniqueClothingModel constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !clothingModelRepository.existsByName(value);
    }

}