package ua.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ua.entity.SectionOfClothes;
import ua.repository.SectionOfClothesRepository;

@Component
public class SectionOfClothesConverter implements Converter<String, SectionOfClothes> {

    private final SectionOfClothesRepository sectionOfClothesRepository;

    public SectionOfClothesConverter(SectionOfClothesRepository sectionOfClothesRepository) {
        this.sectionOfClothesRepository = sectionOfClothesRepository;
    }

    @Override
    public SectionOfClothes convert(String source) {
        return sectionOfClothesRepository.findByName(source);
    }

}