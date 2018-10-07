package ua.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.TypeOfClothes;
import ua.model.filter.SimpleFilter;

public interface TypeOfClothesService extends CrudService<TypeOfClothes, Integer> {

    Page<TypeOfClothes> findAll(Pageable pageable, SimpleFilter filter);

}