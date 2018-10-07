package ua.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Season;
import ua.model.filter.SimpleFilter;

public interface SeasonService extends CrudService<Season, Integer> {

    Page<Season> findAll(Pageable pageable, SimpleFilter filter);

}