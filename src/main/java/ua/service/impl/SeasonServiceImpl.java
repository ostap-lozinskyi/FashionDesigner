package ua.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.entity.Season;
import ua.model.filter.SimpleFilter;
import ua.repository.SeasonRepository;
import ua.service.SeasonService;

@Service
public class SeasonServiceImpl extends CrudServiceImpl<Season, Integer> implements SeasonService {

    private final SeasonRepository seasonRepository;

    @Autowired
    public SeasonServiceImpl(SeasonRepository seasonRepository) {
        super(seasonRepository);
        this.seasonRepository = seasonRepository;
    }

    @Override
    public Page<Season> findAll(Pageable pageable, SimpleFilter filter) {
        return seasonRepository.findAll(filter(filter), pageable);
    }

    private Specification<Season> filter(SimpleFilter filter) {
        return (root, query, cb) -> {
            if (filter.getSearch().isEmpty()) return null;
            return cb.like(root.get("name"), filter.getSearch() + "%");
        };
    }
}