package ua.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.SectionOfClothes;
import ua.model.filter.SimpleFilter;

public interface SectionOfClothesService extends CrudService<SectionOfClothes, Integer> {

    Page<SectionOfClothes> findAll(Pageable pageable, SimpleFilter filter);

}