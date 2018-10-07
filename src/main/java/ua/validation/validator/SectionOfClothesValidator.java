package ua.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.repository.SectionOfClothesRepository;
import ua.validation.annotation.UniqueSectionOfClothes;

@Component
public class SectionOfClothesValidator implements ConstraintValidator<UniqueSectionOfClothes, String> {

    private final SectionOfClothesRepository sectionOfClothesRepository;

    public SectionOfClothesValidator(SectionOfClothesRepository sectionOfClothesRepository) {
        this.sectionOfClothesRepository = sectionOfClothesRepository;
    }

    @Override
    public void initialize(UniqueSectionOfClothes constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !sectionOfClothesRepository.existsByName(value);
    }
}