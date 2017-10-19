package ua.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ua.entity.Article;
import ua.repository.ArticleRepository;

@Component
public class ArticleConverter implements Converter<String, Article> {

	private final ArticleRepository articleRepository;

	public ArticleConverter(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	@Override
	public Article convert(String source) {
		return articleRepository.findByTitle(source);
	}

}
