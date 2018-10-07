package ua.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.entity.TypeOfClothes;
import ua.model.filter.SimpleFilter;
import ua.repository.TypeOfClothesRepository;
import ua.service.TypeOfClothesService;

@Service
public class TypeOfClothesServiceImpl extends CrudServiceImpl<TypeOfClothes, Integer> implements TypeOfClothesService {

    private final TypeOfClothesRepository typeOfClothesRepository;

    @Autowired
    public TypeOfClothesServiceImpl(TypeOfClothesRepository typeOfClothesRepository) {
        super(typeOfClothesRepository);
        this.typeOfClothesRepository = typeOfClothesRepository;
    }

    @Override
    public Page<TypeOfClothes> findAll(Pageable pageable, SimpleFilter filter) {
        return typeOfClothesRepository.findAll(filter(filter), pageable);
    }

    private Specification<TypeOfClothes> filter(SimpleFilter filter) {
        return (root, query, cb) -> {
            if (filter.getSearch().isEmpty()) return null;
            return cb.like(root.get("name"), filter.getSearch() + "%");
        };
    }
}