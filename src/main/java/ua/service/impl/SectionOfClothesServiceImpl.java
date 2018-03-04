package ua.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.entity.SectionOfClothes;
import ua.model.filter.SimpleFilter;
import ua.repository.SectionOfClothesRepository;
import ua.service.SectionOfClothesService;

@Service
public class SectionOfClothesServiceImpl extends CrudServiceImpl<SectionOfClothes, Integer> implements SectionOfClothesService {

	private final SectionOfClothesRepository sectionOfClothesRepository;
	
	@Autowired
	public SectionOfClothesServiceImpl(SectionOfClothesRepository sectionOfClothesRepository) {
		super(sectionOfClothesRepository);
		this.sectionOfClothesRepository = sectionOfClothesRepository;
	}

	@Override
	public Page<SectionOfClothes> findAll(Pageable pageable, SimpleFilter filter) {
		return sectionOfClothesRepository.findAll(filter(filter), pageable);
	}
	
	private Specification<SectionOfClothes> filter(SimpleFilter filter){
		return (root, query, cb) -> {
			if(filter.getSearch().isEmpty()) return null;
			return cb.like(root.get("name"), filter.getSearch()+"%");
		};
	}

}
