package ua.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ua.entity.TypeOfClothes;
import ua.repository.TypeOfClothesRepository;

@Component
public class TypeOfCollectionConverter implements Converter<String, TypeOfClothes> {

	private final TypeOfClothesRepository typeOfCollectionRepository;

	public TypeOfCollectionConverter(TypeOfClothesRepository typeOfCollectionRepository) {
		this.typeOfCollectionRepository = typeOfCollectionRepository;
	}

	@Override
	public TypeOfClothes convert(String source) {
		return typeOfCollectionRepository.findByName(source);
	}

}
