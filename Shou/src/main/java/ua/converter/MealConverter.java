package ua.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ua.entity.Meal;
import ua.repository.MealRepository;

@Component
public class MealConverter implements Converter<String, Meal> {

	private final MealRepository repository;

	public MealConverter(MealRepository repository) {
		this.repository = repository;
	}

	@Override
	public Meal convert(String source) {
		return repository.findByName(source);
	}

}
