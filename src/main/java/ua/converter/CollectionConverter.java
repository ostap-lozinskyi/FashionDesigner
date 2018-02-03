package ua.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ua.entity.Collection;
import ua.repository.CollectionRepository;

@Component
public class CollectionConverter implements Converter<String, Collection> {

	private final CollectionRepository collectionRepository;

	public CollectionConverter(CollectionRepository collectionRepository) {
		this.collectionRepository = collectionRepository;
	}

	@Override
	public Collection convert(String source) {
		return collectionRepository.findByName(source);
	}

}
