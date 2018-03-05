package ua.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ua.entity.TypeOfClothes;
import ua.repository.TypeOfClothesRepository;

@Component
public class TypeOfClothesConverter implements Converter<String, TypeOfClothes> {

	private final TypeOfClothesRepository typeOfClothesRepository;

	public TypeOfClothesConverter(TypeOfClothesRepository typeOfClothesRepository) {
		this.typeOfClothesRepository = typeOfClothesRepository;
	}

	@Override
	public TypeOfClothes convert(String source) {
		return typeOfClothesRepository.findByName(source);
	}

}
