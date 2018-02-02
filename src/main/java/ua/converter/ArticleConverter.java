package ua.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ua.entity.Collection;
import ua.repository.CollectionRepository;

@Component
public class ArticleConverter implements Converter<String, Collection> {

	private final CollectionRepository articleRepository;

	public ArticleConverter(CollectionRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	@Override
	public Collection convert(String source) {
		return articleRepository.findByTitle(source);
	}

}
