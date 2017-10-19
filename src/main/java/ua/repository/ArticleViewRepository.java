package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.model.filter.ArticleFilter;
import ua.model.view.MealIndexView;
import ua.model.view.ArticleView;

public interface ArticleViewRepository {

	Page<ArticleView> findAllMealView(ArticleFilter filter, Pageable pageable);
		
}
