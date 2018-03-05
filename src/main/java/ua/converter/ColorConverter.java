package ua.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ua.entity.Color;
import ua.repository.ColorRepository;

@Component
public class ColorConverter implements Converter<String, Color> {

	private final ColorRepository colorRepository;

	public ColorConverter(ColorRepository colorRepository) {
		this.colorRepository = colorRepository;
	}

	@Override
	public Color convert(String source) {
		return colorRepository.findByName(source);
	}

}
