package ua.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.entity.Ms;
import ua.model.filter.SimpleFilter;
import ua.repository.MsRepository;
import ua.service.MsService;

@Service
public class MsServiceImpl extends CrudServiceImpl<Ms, Integer> implements MsService {

	private final MsRepository msRepository;

	@Autowired
	public MsServiceImpl(MsRepository msRepository) {
		super(msRepository);
		this.msRepository = msRepository;
	}

	@Override
	public Page<Ms> findAll(Pageable pageable, SimpleFilter filter) {
		return msRepository.findAll(filter(filter), pageable);
	}
	
	private Specification<Ms> filter(SimpleFilter filter){
		return (root, query, cb) -> {
			if(filter.getSearch().isEmpty()) return null;
			return cb.like(root.get("name"), filter.getSearch()+"%");
		};
	}

}
