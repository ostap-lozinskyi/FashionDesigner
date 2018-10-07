package ua.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Color;
import ua.model.filter.SimpleFilter;

public interface ColorService extends CrudService<Color, Integer> {

    Page<Color> findAll(Pageable pageable, SimpleFilter filter);

}