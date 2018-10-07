package ua.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.entity.Color;
import ua.model.filter.SimpleFilter;
import ua.repository.ColorRepository;
import ua.service.ColorService;

@Service
public class ColorServiceImpl extends CrudServiceImpl<Color, Integer> implements ColorService {

    private final ColorRepository colorRepository;

    @Autowired
    public ColorServiceImpl(ColorRepository colorRepository) {
        super(colorRepository);
        this.colorRepository = colorRepository;
    }

    @Override
    public Page<Color> findAll(Pageable pageable, SimpleFilter filter) {
        return colorRepository.findAll(filter(filter), pageable);
    }

    private Specification<Color> filter(SimpleFilter filter) {
        return (root, query, cb) -> {
            if (filter.getSearch().isEmpty()) return null;
            return cb.like(root.get("name"), filter.getSearch() + "%");
        };
    }
}