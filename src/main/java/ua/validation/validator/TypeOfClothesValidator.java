package ua.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.repository.TypeOfClothesRepository;
import ua.validation.annotation.UniqueTypeOfClothes;

@Component
public class TypeOfClothesValidator implements ConstraintValidator<UniqueTypeOfClothes, String> {

    private final TypeOfClothesRepository typeOfClothesRepository;

    public TypeOfClothesValidator(TypeOfClothesRepository typeOfClothesRepository) {
        this.typeOfClothesRepository = typeOfClothesRepository;
    }

    @Override
    public void initialize(UniqueTypeOfClothes constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !typeOfClothesRepository.existsByName(value);
    }
}