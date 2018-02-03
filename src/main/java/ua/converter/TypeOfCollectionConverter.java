package ua.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ua.entity.TypeOfCollection;
import ua.repository.TypeOfCollectionRepository;

@Component
public class TypeOfCollectionConverter implements Converter<String, TypeOfCollection> {

	private final TypeOfCollectionRepository typeOfCollectionRepository;

	public TypeOfCollectionConverter(TypeOfCollectionRepository typeOfCollectionRepository) {
		this.typeOfCollectionRepository = typeOfCollectionRepository;
	}

	@Override
	public TypeOfCollection convert(String source) {
		return typeOfCollectionRepository.findByName(source);
	}

}
