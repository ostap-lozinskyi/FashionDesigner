package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.model.filter.ComponentFilter;
import ua.model.view.ComponentView;

public interface ComponentViewRepository {

	Page<ComponentView> findAllView(ComponentFilter filter, Pageable pageable);
		
}
