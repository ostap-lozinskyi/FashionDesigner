package ua.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ua.entity.Season;
import ua.repository.SeasonRepository;

@Component
public class SeasonConverter implements Converter<String, Season> {

	private final SeasonRepository seasonRepository;

	public SeasonConverter(SeasonRepository seasonRepository) {
		this.seasonRepository = seasonRepository;
	}

	@Override
	public Season convert(String source) {
		return seasonRepository.findByName(source);
	}

}
